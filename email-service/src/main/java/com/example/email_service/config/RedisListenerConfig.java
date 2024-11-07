package com.example.email_service.config;

import com.example.email_service.service.RedisMessageSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
public class RedisListenerConfig {


    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.channel-name}")
    private String redisChannelName;

    private final RedisMessageSubscriber redisMessageSubscriber;
    // Mengatur koneksi ke redis
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHost,redisPort);
        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean
    public MessageListener messageListener(){
        return new MessageListenerAdapter(redisMessageSubscriber);
    }
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        return container;
    }

    @Bean
    public ChannelTopic channelTopic(){
        return new ChannelTopic(redisChannelName);
    }


    // Berinteraksi dengan redis
    @Bean
    public RedisTemplate<?,?> redisTemplate(){
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return template;
    }

}
