package com.verby.internalconsumerserver.consumer;

import com.verby.internalconsumerserver.cover.CoverUpdatedEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

    private final RedisTemplate<?, ?> redisTemplate;
    private final CoverUpdatedEventHandler coverExternalEventHandler;

    public RedisMessageSubscriber(RedisTemplate<?, ?> redisTemplate,
                                  RedisMessageListenerContainer redisMessageListener,
                                  CoverUpdatedEventHandler handler) {
        this.redisTemplate = redisTemplate;
        this.coverExternalEventHandler = handler;
        ChannelTopic coverEventChannel = new ChannelTopic("cover_event");
        redisMessageListener.addMessageListener(this, coverEventChannel);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String coverId = redisTemplate.getStringSerializer().deserialize(message.getBody());
        coverExternalEventHandler.handle(Long.valueOf(coverId));
    }
}
