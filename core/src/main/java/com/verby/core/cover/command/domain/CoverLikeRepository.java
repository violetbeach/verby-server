package com.verby.core.cover.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CoverLikeRepository extends Repository<CoverLike, Long> {
    void save(CoverLike coverLike);
    boolean existsByUserIdAndCoverId(long userId, long coverId);
    Optional<CoverLike> findByUserIdAndCoverId(long userId, long coverId);
    void delete(CoverLike coverLike);

}
