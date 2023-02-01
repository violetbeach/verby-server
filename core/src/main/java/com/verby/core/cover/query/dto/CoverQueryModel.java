package com.verby.core.cover.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collation = "cover")
@AllArgsConstructor
public class CoverQueryModel {
    @Id
    private final Long id;
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
    private final Long hits;
}
