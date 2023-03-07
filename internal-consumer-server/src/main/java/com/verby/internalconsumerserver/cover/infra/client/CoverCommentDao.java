package com.verby.internalconsumerserver.cover.infra.client;

import com.verby.internalconsumerserver.cover.infra.dto.CoverComment;
import org.springframework.data.repository.Repository;

public interface CoverCommentDao extends Repository<CoverComment, Long> {

    long countByCoverId(long coverId);

}
