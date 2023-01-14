package com.verby.internalconsumerserver.cover.infra.dto;

import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@Immutable
@Subselect(
         """
         SELECT c.id as id,
             c.contest_id,
             u.login_id as publisher_name,
             c.user_id as publisher_id, c.title, c.content, c.video, c.highlight, c.image, c.hits,
             s.artist_id, a.name as artist_name,
             ct.song_id, s.name as song_name    
         FROM cover c    
         INNER JOIN user u    
             ON c.user_id = u.id    
         LEFT OUTER JOIN contest ct    
             ON c.contest_id = ct.id    
         LEFT OUTER JOIN song s    
             ON ct.song_id = s.id    
         LEFT OUTER Join artist a    
             ON s.artist_id = a.id""")
@Synchronize({ "cover", "user", "contest", "song", "artist" })
public class CoverSummary {
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
