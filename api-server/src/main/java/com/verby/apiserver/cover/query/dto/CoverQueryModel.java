package com.verby.apiserver.cover.query.dto;

import com.verby.core.config.database.RedisHashKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = RedisHashKey.COVER_SUMMARY)
@RequiredArgsConstructor
public class CoverQueryModel {
    @Id
    private Long id;
    private final Long contestId;
    private final Long publisherId;
    private final String publisherName;
    private final String title;
    private final String content;
    private final String video;
    private final String highlight;
    private final String image;
    private final Long artistId;
    private final String artistName;
    private final Long songId;
    private final String songName;
}
