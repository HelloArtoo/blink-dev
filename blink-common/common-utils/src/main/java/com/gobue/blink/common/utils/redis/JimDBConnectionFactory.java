package com.gobue.blink.common.utils.redis;

import com.google.common.collect.Maps;
import com.jd.y.ipc.saas.common.database.MultiTenantDataSource;
import com.jd.y.ipc.saas.common.exception.AppBusinessException;
import com.jd.y.ipc.saas.common.model.RedisInfo;
import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.spring.ApplicationContextHolder;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class JimDBConnectionFactory implements RedisConnectionFactory {

    private MethodInterceptor interceptor;

    private RedisConnectionFactory actual;

    private DataSource masterDataSource;

    private JdbcTemplate jdbcTemplate;

    private Object lock = new Object();

    private static final String sql = "select tenant_id, host, password, port, timeout from redis_info where tenant_id = ?";

    ConcurrentMap<String, JedisConnectionFactory> jedisConnectionFactoryMap = Maps.newConcurrentMap();
    ConcurrentMap<String, RedisInfo> redisInfoFactoryMap = Maps.newConcurrentMap();

    public JimDBConnectionFactory(RedisConnectionFactory actual, MethodInterceptor interceptor) {

        this.interceptor = interceptor;
        this.actual = actual;
    }

    protected RedisConnection postProcessConnection(RedisConnection connection) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(connection);
        proxyFactory.addAdvice(interceptor);
        return (JedisConnection) (proxyFactory.getProxy());
    }

    @Override
    public RedisConnection getConnection() throws AppBusinessException{
        JedisConnectionFactory factory = buildJedisConnectionFactory();
        return postProcessConnection(factory.getConnection());
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        JedisConnectionFactory factory = buildJedisConnectionFactory();
        return factory.getClusterConnection();
//        return actual.getClusterConnection();
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        JedisConnectionFactory factory = buildJedisConnectionFactory();
        return factory.getConvertPipelineAndTxResults();
//        return actual.getConvertPipelineAndTxResults();
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        JedisConnectionFactory factory = buildJedisConnectionFactory();
        return factory.getSentinelConnection();
//        return actual.getSentinelConnection();
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException e) {
        JedisConnectionFactory factory = buildJedisConnectionFactory();
        return factory.translateExceptionIfPossible(e);
//        return actual.translateExceptionIfPossible(e);
    }

    private JedisConnectionFactory buildJedisConnectionFactory() {
        SaaSContext saaSContext = ThreadLocalUtils.getSaaSContext();
        if(Objects.isNull(saaSContext)) {
            throw new AppBusinessException("saas context is null");
        }

        String tenantId = saaSContext.getTenantId();
        if(StringUtils.isEmpty(tenantId)) {
            throw new AppBusinessException("tenant id is null");
        }

        synchronized (lock) {
            //TODO 最好加个更新标示，为了确认是否从本地缓存中获取信息
            RedisInfo redisInfo = redisInfoFactoryMap.get(tenantId);
            if (redisInfo == null) {
                // 获取master库数据连接
                if (masterDataSource == null) {
                    masterDataSource = ApplicationContextHolder.getBean(DataSource.class);
                    if (masterDataSource instanceof MultiTenantDataSource) {
                        this.masterDataSource = ((MultiTenantDataSource) masterDataSource).getMasterDataSource();
                    }
                }

                // 查询当前租户使用的redis配置,如果SaaSContext含有就无需查询数据库
                try {
                    jdbcTemplate = new JdbcTemplate(masterDataSource);
                    redisInfo = jdbcTemplate.queryForObject(sql, new Object[]{tenantId}, new BeanPropertyRowMapper<RedisInfo>(RedisInfo.class));
                } catch (Exception e) {
                    throw new AppBusinessException("Queries the tenant's Redis configuration error");
                }

                if (redisInfo == null || StringUtils.isEmpty(redisInfo.getHost()) || StringUtils.isEmpty(redisInfo.getPassword())
                        || StringUtils.isEmpty(redisInfo.getPort())) {
                    throw new AppBusinessException("Not get the config of the tenant's Redis");
                }

                redisInfoFactoryMap.put(tenantId, redisInfo);
            }

            String mapKey = redisInfo.getHost() + ":" + redisInfo.getPort();
            JedisConnectionFactory jedisConnFactory = jedisConnectionFactoryMap.get(mapKey);
            // 本地缓存中没有,重新构造
            if (jedisConnFactory == null) {
                jedisConnFactory = new JedisConnectionFactory();
                jedisConnFactory.setHostName(redisInfo.getHost());
                jedisConnFactory.setPassword(redisInfo.getPassword());
                jedisConnFactory.setPort(redisInfo.getPort());
                jedisConnFactory.setTimeout(redisInfo.getTimeout() == null ? 3000 : redisInfo.getTimeout());
                jedisConnFactory.afterPropertiesSet();

                jedisConnectionFactoryMap.put(mapKey, jedisConnFactory);
            }
            return jedisConnFactory;
        }
    }
}
