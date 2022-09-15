package com.verby.restapi.artist.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "artist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistData {

    @Id
    private Long id;

    private String name;

    public ArtistData(String name) {
        this.name = name;
    }
}
