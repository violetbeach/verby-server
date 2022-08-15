package com.verby.restapi.artist.command.application;

import com.verby.restapi.artist.command.domain.Artist;
import com.verby.restapi.artist.command.domain.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository accountRepository;

    public Artist create(CreateArtistRequest createArtistRequest) {
        Artist artist = new Artist(createArtistRequest.getName());
        return accountRepository.save(artist);
    }

}
