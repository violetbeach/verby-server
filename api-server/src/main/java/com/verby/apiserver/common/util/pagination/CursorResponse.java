package com.verby.apiserver.common.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CursorResponse<T> {
    List<T> data;
    CursorRequest next;
}
