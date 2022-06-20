package edu.fy.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.spring.data.redis.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 *
 * @author YangAo
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // fastjson
        FastJsonRedisSerializer<Object> objectRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        objectRedisSerializer.getFastJsonConfig().setWriterFeatures(JSONWriter.Feature.WriteClassName);
        objectRedisSerializer.getFastJsonConfig().setReaderFeatures(JSONReader.Feature.SupportAutoType);

        // jackson
        /* Jackson2JsonRedisSerializer<Object> objectRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // 忽略多余字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectRedisSerializer.setObjectMapper(mapper); */

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key 采用 String 的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash 的 key 也采用 String 的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value 序列化方式采用
        redisTemplate.setValueSerializer(objectRedisSerializer);
        // hash 的 value 序列化方式采用
        redisTemplate.setHashValueSerializer(objectRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
