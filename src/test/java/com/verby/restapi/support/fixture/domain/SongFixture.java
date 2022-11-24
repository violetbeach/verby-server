package com.verby.restapi.support.fixture.domain;

import com.verby.restapi.song.command.domain.Song;

public enum SongFixture {

    좋은_날("좋은 날", "/test/image/good_day.png");

    private final String name;
    private final String image;

    SongFixture(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Song getSong(long artistId) {
        return new Song(artistId, name, image);
    }

}
