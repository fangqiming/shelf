package com.i000.stock.user.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:09 2018/4/26
 * @Modified By:
 */
@Data
public class Plan {

    @TableId
    private Long id;
    private LocalDate newDate;
    private String name;
    private String type;
    private String action;
    private BigDecimal rank;
    private String note;


}
