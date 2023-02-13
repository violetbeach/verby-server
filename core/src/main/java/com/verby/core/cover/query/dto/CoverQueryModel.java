package com.verby.core.cover.query.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Document("covers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CoverQueryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Long contestId;
    private Long publisherId;
    private String publisherName;
    private String title;
    private String content;
    private String video;
    private String highlight;
    private String image;
    private Long artistId;
    private String artistName;
    private Long songId;
    private String songName;
    private Long hits;
    
}
