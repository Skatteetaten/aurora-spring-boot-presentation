package com.example.demowebcache;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

// tag::cache-config[]
@EnableCaching
@Configuration
public class ApplicationConfiguration {
// end::cache-config[]

    // tag::cache-config-json[]
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        SerializationPair<Object> jsonSerializer = fromSerializer(new GenericJackson2JsonRedisSerializer());
        return RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(defaultCacheConfig().serializeValuesWith(jsonSerializer))
            .build();
    }
    // end::cache-config-json[]
}
