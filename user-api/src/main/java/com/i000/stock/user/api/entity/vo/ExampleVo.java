package com.i000.stock.user.api.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.i000.stock.user.core.jackson.serialize.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 12:25 2018/4/23
 * @Modified By:
 */
@Data
public class ExampleVo {

    @NotNull(message = "名称不能为空")
    private String name;

    @Range(message = "年龄不能小于0")
    private Integer age;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedTime;
}
