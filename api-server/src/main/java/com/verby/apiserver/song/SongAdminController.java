package com.verby.apiserver.song;

import com.verby.core.song.command.application.CreateSongRequest;
import com.verby.core.song.command.application.SongService;
import com.verby.core.song.command.domain.Song;
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

    @PostMapping("/artists/{artistId}/songs")
    private ResponseEntity<Song> create(@PathVariable Long artistId, @RequestBody @Valid CreateSongRequest createSongRequest) {
        createSongRequest.setArtistId(artistId);

        Song song = songService.create(createSongRequest);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

}
