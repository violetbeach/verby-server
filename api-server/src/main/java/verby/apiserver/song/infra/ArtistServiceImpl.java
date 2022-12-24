package verby.apiserver.song.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.artist.query.ArtistDataDao;
import verby.apiserver.song.command.domain.ArtistService;

@RequiredArgsConstructor
@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDataDao artistDataDao;

    @Override
    public boolean existsArtist(Long artistId) {
        return artistDataDao.existsById(artistId);
    }

}
