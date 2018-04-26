package com.i000.stock.user.api.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 9:33 2018/4/26
 * @Modified By:
 */
@Data
public class PriceVos {

    private List<String> name = new ArrayList<>();

    private List<String> code = new ArrayList<>();

    private List<BigDecimal> open = new ArrayList<>();

    private List<BigDecimal> close = new ArrayList<>();

    private List<BigDecimal> volume = new ArrayList<>();

    private List<BigDecimal> high = new ArrayList<>();

    private List<BigDecimal> low = new ArrayList<>();

    private String date;

    private List<BigDecimal> buy = new ArrayList<>();

    private List<BigDecimal> sell = new ArrayList<>();

    private List<Byte> isOpen = new ArrayList<>();
}
