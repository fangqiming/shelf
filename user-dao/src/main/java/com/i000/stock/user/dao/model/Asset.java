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
public class Asset {

    @TableId
    private Long id;

    private LocalDate date;

    /**
     * 股票余额
     */
    private BigDecimal stock;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 做空金额
     */
    private BigDecimal cover;
}
