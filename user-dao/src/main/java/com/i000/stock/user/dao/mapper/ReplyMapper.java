package com.i000.stock.user.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.i000.stock.user.dao.entity.bo.BaseSearchVo;
import com.i000.stock.user.dao.model.Reply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:34 2018/4/24
 * @Modified By:
 */
public interface ReplyMapper extends BaseMapper<Reply> {
    /**
     * 分页查询某一个话题的回复
     *
     * @param baseSearchVo
     * @param id
     * @return
     */
    List<Reply> search(@Param("baseSearchVo") BaseSearchVo baseSearchVo, @Param("id")Long id);

    /**
     * 获取分页查询的总条数
     *
     * @return
     */
    Long count();

    /**
     * 根据id查询回复信息
     *
     * @param id
     * @return
     */
    List<Reply> find(@Param("id") Long id);


    @Update("update reply set good_num=good_num+1 where id=${id}")
    Long doGood(@Param("id") Long id);

    @Update("update reply set bad_num=bad_num+1 where id=${id}")
    Long doBad(@Param("id") Long id);
}
