package com.verby.core.common.event.internal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class InternalEventPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public InternalEventPublisher(@Qualifier("messageRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(ChannelTopic topic, Long message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}