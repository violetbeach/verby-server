package com.verby.restapi.song.command.application;

import com.verby.restapi.common.storage.StaticStorage;
import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.song.command.domain.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final StaticStorage staticStorage;

    @Value("${static.paths.song.image}")
    private String imageBasePath;

    public Song create(long artistsId, CreateSongRequest createSongRequest, MultipartFile image) {
        String imagePath = staticStorage.upload(image, imagePath);

        Song song = new Song(
                artistsId,
                createSongRequest.getName(),
                imagePath);

        return songRepository.save(song);
    }

}
