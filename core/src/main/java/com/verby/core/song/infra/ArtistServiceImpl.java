package com.verby.core.song.infra;

import com.verby.core.artist.query.ArtistDataDao;
import com.verby.core.song.command.domain.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ArtistServiceImpl implements ArtistService {

    private final ArtistDataDao artistDataDao;

    @Override
    public boolean existsArtist(Long artistId) {
        return artistDataDao.existsById(artistId);
    }

}
