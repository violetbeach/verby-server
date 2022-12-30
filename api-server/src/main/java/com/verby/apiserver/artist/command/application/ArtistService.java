package com.verby.apiserver.artist.command.application;

import com.verby.apiserver.artist.command.domain.Artist;
import com.verby.apiserver.artist.command.domain.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Artist create(CreateArtistRequest request) {
        Artist artist = new Artist(request.getName());
        return artistRepository.save(artist);
    }

}
