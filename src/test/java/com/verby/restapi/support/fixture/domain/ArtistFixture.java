package com.verby.restapi.support.fixture.domain;

import com.verby.restapi.artist.command.domain.Artist;

public enum ArtistFixture {

    IU("IU");

    private final String name;

    ArtistFixture(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return new Artist(name);
    }
}
