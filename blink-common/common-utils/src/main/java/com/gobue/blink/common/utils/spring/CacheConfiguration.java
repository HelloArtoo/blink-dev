package com.gobue.blink.common.utils.spring;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jd.y.ipc.saas.common.redis.JimDBConnectionFactory;
import com.jd.y.ipc.saas.common.redis.JimDBMethodInterceptor;
import com.jd.y.ipc.saas.common.utils.JsonUtils;
import com.jd.y.ipc.saas.common.utils.RedisUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableCaching
@ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate")
public class CacheConfiguration extends CachingConfigurerSupport implements BeanPostProcessor {

    // default key generator
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            List<String> keys = Lists.newArrayList();

            keys.add(target.getClass().getName());
            keys.add(method.getName());

            String hasCode = "";
            for (Object obj : params) {
                if (Objects.nonNull(obj)) {
                    hasCode += obj.hashCode();
                }
            }
            return Joiner.on("#").join(keys) + "#" + hasCode;
        };

    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }


    // jimDB does not support 'multi' and 'exec' command, and it is not a cluster redis setup
    // in order to bypass these unsupported commands
    // delegate RedisConnectionFactory with JimDBConnectionFactory and intercept all methods in redisConnection
    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        if (bean instanceof RedisConnectionFactory) {
            RedisConnectionFactory redisConnectionFactory = (RedisConnectionFactory)bean;
            return new JimDBConnectionFactory(redisConnectionFactory, new JimDBMethodInterceptor());
        }
        return bean;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(7200);
        redisCacheManager.setLoadRemoteCachesOnStartup(false);
//        redisCacheManager.setUsePrefix(true);
//        redisCacheManager.setCachePrefix(new RedisCachePrefix() {
//            @Override
//            public byte[] prefix(String cacheName) {
//                return RedisUtils.getKeyWithNamespace(cacheName).getBytes();
//            }
//        });
        return redisCacheManager;
    }



    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        objectJackson2JsonRedisSerializer.setObjectMapper(JsonUtils.CACHE_MAPPER);

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new ObjectRedisSerializer());
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        template.setValueSerializer(objectJackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        objectJackson2JsonRedisSerializer.setObjectMapper(JsonUtils.CACHE_MAPPER);

        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new ObjectRedisSerializer());
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        template.setValueSerializer(objectJackson2JsonRedisSerializer);
        return template;
    }


    public static class ObjectRedisSerializer implements RedisSerializer<Object> {

        private final Charset charset;

        public ObjectRedisSerializer() {
            this(Charset.forName("UTF8"));
        }

        public ObjectRedisSerializer(Charset charset) {
            Assert.notNull(charset, "Charset must not be null!");
            this.charset = charset;
        }

        @Override
        public String deserialize(byte[] bytes) {
            return (bytes == null ? null : new String(bytes, charset));
        }

        @Override
        public byte[] serialize(Object key) {
            return (key == null ? null : RedisUtils.getKeyWithNamespace(key.toString()).toString().getBytes(charset));
        }
    }
}
