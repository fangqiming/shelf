package com.i000.stock.user.api.service;

import com.i000.stock.user.api.entity.bo.Page;
import com.i000.stock.user.dao.entity.bo.BaseSearchVo;
import com.i000.stock.user.dao.model.Topic;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:36 2018/4/24
 * @Modified By:
 */
public interface TopicService {

    /**
     * 创建话题
     *
     * @param topic
     * @return
     */
    Long create(Topic topic);

    /**
     * 分页查询话题
     *
     * @param search
     * @return
     */
    Page<Topic> search(BaseSearchVo search);

    /**
     * 对话题点赞 ，非重要
     *
     * @return
     */
    Long doLike(Long id);

    /**
     * 根据话题主键获取话题
     *
     * @param id
     * @return
     */
    Topic get(Long id);

}
