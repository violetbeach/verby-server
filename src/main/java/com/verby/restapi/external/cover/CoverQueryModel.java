package com.verby.restapi.external.cover;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

import static com.verby.restapi.config.database.RedisHashKey.COVER_SUMMARY;

@Getter
@RedisHash(value = COVER_SUMMARY)
@RequiredArgsConstructor
public class CoverQueryModel {
    @Id
    private Long id;
    @Indexed
    private final long coverId;
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
