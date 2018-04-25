package com.i000.stock.user.api.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.i000.stock.user.core.jackson.serialize.LocalDateTimeSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 18:10 2018/4/24
 * @Modified By:
 */
@Data
public class TopicVo {

    private Long id;

    @NotNull(message = "内容不能为空")
    private String content;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "用户码不能为空")
    private String userCode;

    private Integer clickNum;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdTime;

}
