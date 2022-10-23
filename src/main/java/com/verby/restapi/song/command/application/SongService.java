package com.verby.restapi.song.command.application;

import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.song.command.domain.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private final SongStorageService songStorageRepository;

    public Song create(CreateSongRequest createSongRequest, MultipartFile image) {
        String imagePath = songStorageRepository.upload(image);

        Song song = new Song(
                createSongRequest.getArtistId(),
                createSongRequest.getName(),
                imagePath);

        return songRepository.save(song);
    }

}
