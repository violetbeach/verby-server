package com.verby.restapi.contest.infra;

import com.verby.restapi.contest.command.domain.SongService;
import com.verby.restapi.song.query.SongDataDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {

    private final SongDataDao songDataDao;

    @Override
    public boolean existsSong(Long songId) {
        return songDataDao.existsById(songId);
    }

}
