package com.i000.stock.user.core.result;

import com.i000.stock.user.core.result.base.BaseResult;
import lombok.Getter;

class SingleResult<T> extends BaseResult<T> {

    private static final long serialVersionUID = -5848222953862452406L;

    @Getter
    private T entity;

    public SingleResult() {
    }

    SingleResult(T entity) {
        this.entity = entity;
    }
}