package com.i000.stock.user.api.entity.vo;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.i000.stock.user.core.jackson.serialize.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 9:48 2018/4/25
 * @Modified By:
 */
@Data
public class ReplyVo {

    private Long id;

    private Long topicId;

    @NotBlank(message = "用户码不能为空")
    private String userCode;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotNull(message = "回复id不能为空")
    private Long replyId;

    private Long goodNum;

    private Long badNum;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdTime;

}
