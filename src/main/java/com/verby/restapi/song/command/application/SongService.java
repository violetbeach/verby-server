package com.verby.restapi.song.command.application;

import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.song.command.domain.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository accountRepository;

    public Song create(long artistsId, CreateSongRequest createSongRequest) {
        Song artist = new Song(
                artistsId,
                createSongRequest.getName(),
                createSongRequest.getImage());

        return accountRepository.save(artist);
    }

}
