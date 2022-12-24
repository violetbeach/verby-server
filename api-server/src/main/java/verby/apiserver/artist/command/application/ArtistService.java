package verby.apiserver.artist.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.artist.command.domain.Artist;
import verby.apiserver.artist.command.domain.ArtistRepository;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Artist create(CreateArtistRequest request) {
        Artist artist = new Artist(request.getName());
        return artistRepository.save(artist);
    }

}
