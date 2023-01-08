package com.verby.batchserver.cover;

import com.verby.core.cover.Cover;
import com.verby.core.cover.CoverHit;
import com.verby.core.cover.command.domain.CoverHitRepository;
import com.verby.core.cover.command.domain.CoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WriteBackCoverHitsScheduler {

    private final CoverHitRepository coverHitRepository;
    private final CoverRepository coverRepository;

    @Scheduled(cron = "0 5 * * * *")
    public void execute() {
        List<CoverHit> hits = coverHitRepository.findAll();
        hits.forEach((hit) -> {
            Optional<Cover> cover = coverRepository.findById(hit.getCoverId());
            cover.ifPresent(c -> c.hit(hit.getRequestIPs().size()));
            coverHitRepository.delete(hit);
        });
    }

}
