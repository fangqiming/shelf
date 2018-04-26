package com.i000.stock.user.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:10 2018/4/26
 * @Modified By:
 */
@Data
public class Trade {

    @TableId
    private Long id;

    private String name;
    private String action;
    private String type;
    private LocalDate oldDate;
    private BigDecimal oldPrice;
    private LocalDate newDate;
    private BigDecimal newPrice;
    private BigDecimal gain;
    private String note;


}
