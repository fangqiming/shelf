package com.i000.stock.user.api.entity.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 10:35 2018/4/25
 * @Modified By:
 */
@Data
public class ReplyFirstVo {

    private Long id;

    @NotNull(message = "话题主键不能为空")
    private Long topicId;

    @NotBlank(message = "用户码不能为空")
    private String userCode;

    @NotBlank(message = "内容不能不空")
    private String content;

}
