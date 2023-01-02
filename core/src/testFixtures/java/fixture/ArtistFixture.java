package fixture;


import com.verby.core.artist.command.domain.Artist;

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
