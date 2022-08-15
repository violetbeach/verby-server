package com.verby.restapi.artist.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "artist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public ArtistData(String name) {
        this.name = name;
    }
}
