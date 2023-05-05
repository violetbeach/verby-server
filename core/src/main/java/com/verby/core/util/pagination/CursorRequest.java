package com.verby.core.util.pagination;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CursorRequest {
    public static final Long NONE_KEY = -1L;

    private Long key;
    private Integer size;

    public CursorRequest(Long key, Integer size) {
        this.key = key;
        this.size = size == null ? 10 : size;
    }

    public boolean hasKey() {
        return key != null;
    }

    public CursorRequest next(long key) {
        return new CursorRequest(key, size);
    }
}

