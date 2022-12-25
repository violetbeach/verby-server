package verby.batchserver.cover;

import org.springframework.data.repository.Repository;
import verby.core.cover.CoverHit;

import java.util.List;

public interface CoverHitRepository extends Repository<CoverHit, Long> {
    List<CoverHit> findAll();
}