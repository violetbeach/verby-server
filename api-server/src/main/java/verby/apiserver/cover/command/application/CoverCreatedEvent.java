package verby.apiserver.cover.command.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import verby.apiserver.cover.command.domain.Cover;

@Getter
@RequiredArgsConstructor
public class CoverCreatedEvent {

    private final Cover cover;

}
