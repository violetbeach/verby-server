package com.verby.restapi.song.command.application;

import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.song.command.domain.SongRepository;
import com.verby.restapi.song.infra.SongS3StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final SongS3StorageService songStorageRepository;

    public Song create(CreateSongRequest createSongRequest) {

        Song song = new Song(
                createSongRequest.getArtistId(),
                createSongRequest.getName(),
                createSongRequest.getImage());

        return songRepository.save(song);
    }

}
