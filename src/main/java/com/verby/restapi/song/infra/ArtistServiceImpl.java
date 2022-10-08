package com.verby.restapi.song.infra;

import com.verby.restapi.artist.query.ArtistDataDao;
import com.verby.restapi.song.command.domain.ArtistService;
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
