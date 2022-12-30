package com.verby.apiserver.song.infra;

import com.verby.apiserver.artist.query.ArtistDataDao;
import com.verby.apiserver.song.command.domain.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDataDao artistDataDao;

    @Override
    public boolean existsArtist(Long artistId) {
        return artistDataDao.existsById(artistId);
    }

}
