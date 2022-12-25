package verby.apiserver.song.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.song.command.domain.Song;
import verby.apiserver.song.command.domain.SongRepository;

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
