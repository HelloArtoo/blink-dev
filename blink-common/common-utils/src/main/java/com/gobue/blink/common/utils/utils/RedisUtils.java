package com.gobue.blink.common.utils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;
import java.util.stream.Collectors;

import static com.jd.y.ipc.saas.common.utils.ThreadLocalUtils.getSaaSContext;

public class RedisUtils {

    public static final String NAMESPACE_SEPARATOR = "::";

    public static <T> RedisTemplate<String, T> generateRedisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper, Class <T> clz) {
        final RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<T> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer <>(clz);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        return template;
    }

    public static String getKeyWithNamespace(String key) {
        return getSaaSContext().getTenantId() + NAMESPACE_SEPARATOR + key;
    }

    public static Map<String, Object> getKeyMapWithNamespace(Map<String,Object>  map) {
        String tenantId = getSaaSContext().getTenantId();
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> tenantId + NAMESPACE_SEPARATOR + entry.getKey(), Map.Entry::getValue));
    }
}
