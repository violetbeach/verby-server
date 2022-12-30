package com.verby.apiserver.song.command.application;

import com.verby.apiserver.song.command.domain.Song;
import com.verby.apiserver.song.command.domain.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public Song create(CreateSongRequest createSongRequest) {

        Song song = new Song(
                createSongRequest.getArtistId(),
                createSongRequest.getName(),
                createSongRequest.getImage());

        return songRepository.save(song);
    }

}
