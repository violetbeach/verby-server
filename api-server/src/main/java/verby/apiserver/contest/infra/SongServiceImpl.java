package verby.apiserver.contest.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.contest.command.domain.SongService;
import verby.apiserver.song.query.SongDataDao;

@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {

    private final SongDataDao songDataDao;

    @Override
    public boolean existsSong(Long songId) {
        return songDataDao.existsById(songId);
    }

}
