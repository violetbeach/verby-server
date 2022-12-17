package com.verby.restapi.external.cover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import static com.verby.restapi.config.database.RedisHashKey.COVER_SUMMARY;

@Getter
@RedisHash(value = COVER_SUMMARY)
@AllArgsConstructor
public class ExternalCoverQueryModel {
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
