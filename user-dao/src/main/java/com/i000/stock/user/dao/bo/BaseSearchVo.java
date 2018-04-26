package com.i000.stock.user.dao.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:43 2018/4/24
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearchVo {

    @Range(min = 1, max = Integer.MAX_VALUE, message = "页大小不能小于1")
    private Integer pageSize;

    @Range(min = 1, max = Integer.MAX_VALUE, message = "查询页不能小于1")
    @NotNull
    private Integer pageNo;

    private Integer start;

    public void setStart() {
        this.start = (pageNo - 1) * pageSize;
    }
}
