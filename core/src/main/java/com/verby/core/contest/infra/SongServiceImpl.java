package com.verby.core.contest.infra;

import com.verby.core.contest.command.domain.SongService;
import com.verby.apiserver.song.query.SongDataDao;
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
