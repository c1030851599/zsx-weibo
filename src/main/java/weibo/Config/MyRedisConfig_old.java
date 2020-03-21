package weibo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig_old {

    @Bean
    public RedisTemplate<Object, String> empRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

//        使用json的序列化器：
        Jackson2JsonRedisSerializer<String> ser = new Jackson2JsonRedisSerializer<String>(String.class);
        template.setDefaultSerializer(ser);
        return template;
    }




}
