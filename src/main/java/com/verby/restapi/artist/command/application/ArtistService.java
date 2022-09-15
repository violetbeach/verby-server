package com.verby.restapi.artist.command.application;

import com.verby.restapi.artist.command.domain.Artist;
import com.verby.restapi.artist.command.domain.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository accountRepository;

    public Artist create(CreateArtistRequest request) {
        Artist artist = new Artist(request.getName());
        return accountRepository.save(artist);
    }

}
