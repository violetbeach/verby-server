package com.verby.internalconsumerserver.cover.infra.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Subselect(
        """
        SELECT c.id as cover_id,
            cl.user_id
        FROM cover c    
        LEFT OUTER JOIN cover_like cl
            ON c.id = cl.cover_id
        """)
@Synchronize({ "cover", "cover_like" })
public class CoverLike {
    @Id
    private long id;
    private Long contestId;
    private long publisherId;
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
