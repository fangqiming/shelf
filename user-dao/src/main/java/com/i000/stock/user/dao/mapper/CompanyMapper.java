package com.i000.stock.user.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.i000.stock.user.dao.bo.BaseSearchVo;
import com.i000.stock.user.dao.model.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:00 2018/4/25
 * @Modified By:
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 清空表结构
     *
     * @return
     */
    @Delete("truncate company")
    Long truncate();

    /**
     * 分页查询公司信息
     *
     * @param baseSearchVo
     * @return
     */
    List<Company> search(@Param("baseSearchVo") BaseSearchVo baseSearchVo);

    /**
     * 获取分页数量
     *
     * @return
     */
    Long count();
}
