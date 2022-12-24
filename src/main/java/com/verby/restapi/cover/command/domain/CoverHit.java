package com.verby.restapi.cover.command.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashSet;
import java.util.Set;

import static com.verby.restapi.config.database.RedisHashKey.COVER_HIT;

@Getter
@RedisHash(value = COVER_HIT)
public class CoverHit {
    @Id
    private Long id;
    private Set<String> requestIPs = new HashSet<>();

    public CoverHit(Long id) {
        this.id = id;
    }

    public void hit(String requestIP) {
        requestIPs.add(requestIP);
    }
}
