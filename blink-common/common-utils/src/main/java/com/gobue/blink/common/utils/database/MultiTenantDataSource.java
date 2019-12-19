package com.gobue.blink.common.utils.database;

import com.google.common.collect.Maps;
import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.model.TenantInfo;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

public class MultiTenantDataSource implements DataSource {

    @Autowired
    private SaaSDatabaseConfig saaSDatabaseConfig;

    // datasource for master database
    private DataSource masterDataSource;

    private JdbcTemplate jdbcTemplate;

    private Object lock = new Object();

    private static final String SEPARATOR = "__";

    private static final String sql = "select * from tenant_infos where tenant_id = ?";

    ConcurrentMap<String, DataSource> tenantDataSource = Maps.newConcurrentMap();
    ConcurrentMap<String, DataSource> connectionSignatureMap = Maps.newConcurrentMap();

    public MultiTenantDataSource(DataSource master, SaaSDatabaseConfig config) {
        this.masterDataSource = master;
        this.jdbcTemplate = new JdbcTemplate(master);
        this.saaSDatabaseConfig = config;
    }

    public DataSource getMasterDataSource() {return masterDataSource;}

    @Override
    public Connection getConnection() throws SQLException {

        // check SaaSContext
        SaaSContext saaSContext = ThreadLocalUtils.getSaaSContext();
        if (Objects.isNull(saaSContext)) {
            throw new SQLException("saas context is null");
        }

        String tenantId = saaSContext.getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            throw new SQLException("tenant id is null");
        }

        // get datasource from in-memory map
        DataSource dataSource = tenantDataSource.get(tenantId);
        if (Objects.nonNull(dataSource)) {
            return _getConnection(dataSource, tenantId);
        }

        // if not in map, create datasource
        synchronized (lock) {
            TenantInfo tenantInfo = jdbcTemplate.queryForObject(sql, new Object[]{tenantId}, new BeanPropertyRowMapper<TenantInfo>(TenantInfo.class));
            if (Objects.isNull(tenantInfo)) {
                throw new SQLException("tenantInfo is null");
            }

            // jdbc:mysql://ip:3306/foobar --> jdbc:mysql://ip:3306 --> jdbc:mysql://ip:3306__username__password
            String url = tenantInfo.getUrl();
            String connectionUrl = url.substring(0, url.lastIndexOf("/"));
            String connectionSignature = connectionUrl + SEPARATOR + tenantInfo.getUser() + SEPARATOR + tenantInfo.getPassword();

            // if same connection exists in map
            dataSource = connectionSignatureMap.get(connectionSignature);
            if (Objects.nonNull(dataSource)) {
                tenantDataSource.put(tenantId, dataSource);
                return _getConnection(dataSource, tenantId);
            }

            // create pooled connection with pre-defined properties
            Class<?> driverType = null;
            String typeName = saaSDatabaseConfig.getConnection().get("type").toString();
            try {
                driverType = Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                throw new SQLException("driver class is null");
            }
            dataSource = DataSourceBuilder.create()
                    .type((Class<? extends DataSource>) driverType)
                    .url(connectionUrl)
                    .username(tenantInfo.getUser())
                    .password(tenantInfo.getPassword())
                    .build();

            (new RelaxedDataBinder(dataSource)).bind(new MutablePropertyValues(saaSDatabaseConfig.getConnection()));

            // save datasource in map
            tenantDataSource.put(tenantId, dataSource);
            connectionSignatureMap.put(connectionSignature, dataSource);

            return _getConnection(dataSource, tenantId);
        }
    }

    private Connection _getConnection(DataSource dataSource, String tenantId) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.prepareStatement("USE " + tenantId).execute();
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
