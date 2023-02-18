package com.verby.core.common.event.internal.infra;

import com.verby.core.common.event.internal.InternalEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisEventPublisher implements InternalEventPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisEventPublisher(@Qualifier("messageRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(InternalEventTopic topic, Object message) {
        redisTemplate.convertAndSend(topic.getName(), message);
    }

}