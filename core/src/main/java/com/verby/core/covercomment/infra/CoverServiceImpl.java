package com.verby.core.covercomment.infra;

import com.verby.core.cover.query.dao.CoverQueryDao;
import com.verby.core.covercomment.command.domain.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    private final CoverQueryDao coverQueryDao;

    public boolean existsById(long id) {
        return coverQueryDao.existsById(id);
    }

}
