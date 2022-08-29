package com.verby.restapi.song.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "song")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongData {

    @Id
    private Long id;

    private long artistId;

    private String name;

    private String image;

}
