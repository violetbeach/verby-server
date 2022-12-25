package verby.apiserver.cover.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CoverHitRepository extends Repository<CoverHit, Long> {
    void save(CoverHit coverHit);
    Optional<CoverHit> findById(Long id);
}
