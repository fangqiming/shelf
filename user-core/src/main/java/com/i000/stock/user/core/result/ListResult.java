package com.i000.stock.user.core.result;

import com.i000.stock.user.core.result.base.BaseResult;
import lombok.Getter;

import java.util.List;

class ListResult<T> extends BaseResult<T> {

    private static final long serialVersionUID = -1891369940301562356L;

    @Getter
    private List<T> entities;

    ListResult() {
    }

    ListResult(List<T> entities) {
        this.entities = entities;
    }
}