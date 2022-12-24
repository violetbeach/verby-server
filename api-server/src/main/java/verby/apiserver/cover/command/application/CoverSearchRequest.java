package verby.apiserver.cover.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import verby.apiserver.common.util.pagination.CursorRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoverSearchRequest {
    private Long contestId;

    @JsonIgnore
    private CursorRequest cursor;

    public CoverSearchRequest(Long contestId) {
        this.contestId = contestId;
    }

    public void setCursor(CursorRequest cursor) {
        this.cursor = cursor;
    }
}
