package verby.apiserver.support.fixture.domain;


import verby.apiserver.song.command.domain.Song;

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
