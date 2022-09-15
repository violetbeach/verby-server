package com.verby.restapi.artist.presentation;

import com.verby.restapi.artist.command.application.ArtistService;
import com.verby.restapi.artist.command.application.CreateArtistRequest;
import com.verby.restapi.artist.command.domain.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/artists")
@RequiredArgsConstructor
public class ArtistAdminController {

    private final ArtistService artistService;

    @PostMapping
    private ResponseEntity<Artist> create(@RequestBody @Valid CreateArtistRequest request) {
        Artist artist = artistService.create(request);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

}
