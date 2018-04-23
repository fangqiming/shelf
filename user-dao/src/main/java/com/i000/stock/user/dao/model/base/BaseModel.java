package com.i000.stock.user.dao.model.base;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseModel {

    @TableId
    private Long id;

    @TableLogic(value = "0", delval = "1")
    private Byte isDeleted;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private LocalDateTime deletedTime;

}