package com.i000.stock.user.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.i000.stock.user.dao.model.Price;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:00 2018/4/25
 * @Modified By:
 */
public interface PriceMapper extends BaseMapper<Price> {
    Price find(@Param("code") String code, @Param("date") String date);

    @Select("select count(*) from `price` where date=${date}")
    Long findCount(@Param("date") String date);
}
