package verby.apiserver.cover.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.cover.command.domain.CoverHit;
import verby.apiserver.cover.command.domain.CoverHitRepository;

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
