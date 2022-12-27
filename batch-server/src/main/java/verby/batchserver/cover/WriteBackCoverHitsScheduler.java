package verby.batchserver.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteBackCoverHitsScheduler {

    private final CoverHitRepository coverHitRepository;

    // TODO
    public void execute() {

    }

}
