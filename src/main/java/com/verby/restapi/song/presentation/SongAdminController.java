package com.verby.restapi.song.presentation;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.song.command.application.CreateSongRequest;
import com.verby.restapi.song.command.application.SongService;
import com.verby.restapi.song.command.domain.ArtistService;
import com.verby.restapi.song.command.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SongAdminController {
    private final SongService songService;
    private final ArtistService artistService;

    @PostMapping("/artists/{artistId}/songs")
    private ResponseEntity<Song> create(@PathVariable Long artistId, @RequestBody @Valid CreateSongRequest createSongRequest) {
        if(!artistService.existsArtist(artistId)) {
            throw new EntityNotFoundException(ErrorCode.ARTIST_NOT_FOUND, "Not found.");
        }
        createSongRequest.setArtistId(artistId);

        Song song = songService.create(createSongRequest);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

}
