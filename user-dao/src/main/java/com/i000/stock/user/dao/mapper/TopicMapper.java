package com.i000.stock.user.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.i000.stock.user.dao.entity.bo.BaseSearchVo;
import com.i000.stock.user.dao.model.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:35 2018/4/24
 * @Modified By:
 */
public interface TopicMapper extends BaseMapper<Topic> {

    /**
     * 分页查询topic
     *
     * @param baseSearchVo
     * @return
     */
    List<Topic> search(@Param("baseSearchVo") BaseSearchVo baseSearchVo);

    /**
     * 计算分页的数据总条数
     *
     * @return
     */
    Long count();
}
