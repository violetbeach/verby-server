package com.verby.core.covercomment.command.domain;

import org.springframework.data.repository.Repository;

public interface CoverCommentRepository extends Repository<CoverComment, Long> {
    CoverComment save(CoverComment coverComment);
}
