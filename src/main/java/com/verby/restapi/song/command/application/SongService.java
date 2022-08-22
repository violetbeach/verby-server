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

    public Song create(CreateSongRequest createSongRequest, MultipartFile image) throws IOException {
        String imagePath = staticStorage.upload(image, imageBasePath);

        Song song = new Song(
                createSongRequest.getArtistId(),
                createSongRequest.getName(),
                imagePath);

        return songRepository.save(song);
    }

}
