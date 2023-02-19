package com.verby.core.cover.infra;

import com.verby.core.contest.query.ContestDataDao;
import com.verby.core.cover.command.domain.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ContestServiceImpl implements ContestService {

    private final ContestDataDao contestDataDao;

    public boolean existsById(Long contestId) {
        return contestDataDao.existsById(contestId);
    }
}
