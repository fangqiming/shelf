package com.i000.stock.user.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.i000.stock.user.dao.model.IndexInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:20 2018/4/25
 * @Modified By:
 */
public interface IndexInfoMapper extends BaseMapper<IndexInfo> {

    List<IndexInfo> find(@Param("date") String date);

    @Select("select count(*) from `index_info` where date=${date}")
    Long findCount(@Param("date") String date);
}
