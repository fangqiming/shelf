package com.i000.stock.user.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 13:59 2018/4/25
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @TableId
    private Long id;

    private String name;

    private String code;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal volume;

    private BigDecimal high;

    private BigDecimal low;

    private String date;

    private BigDecimal buy;

    private BigDecimal sell;

    private Byte isOpen;
}
