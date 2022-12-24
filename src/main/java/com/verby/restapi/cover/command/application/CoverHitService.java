package com.verby.restapi.cover.command.application;

import com.verby.restapi.cover.command.domain.CoverHit;
import com.verby.restapi.cover.command.domain.CoverHitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverHitService {

    private final CoverHitRepository coverHitRepository;

    public void hit(Long coverId, String requestIP) {
        CoverHit coverHit = coverHitRepository.findById(coverId)
                .orElseGet(() -> new CoverHit(coverId));
        coverHit.hit(requestIP);

        coverHitRepository.save(coverHit);
    }

}
