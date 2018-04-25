package com.i000.stock.user.service.impl;

import com.i000.stock.user.api.entity.bo.Page;
import com.i000.stock.user.api.service.TopicService;
import com.i000.stock.user.dao.entity.bo.BaseSearchVo;
import com.i000.stock.user.dao.mapper.TopicMapper;
import com.i000.stock.user.dao.model.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:47 2018/4/24
 * @Modified By:
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Override
    public Long create(Topic topic) {
        topicMapper.insert(topic);
        return topic.getId();
    }

    @Override
    public Page<Topic> search(BaseSearchVo search) {
        search.setStart();
        List<Topic> topicList = topicMapper.search(search);
        Long count = topicMapper.count();
        Page<Topic> result = new Page<>();
        result.setList(topicList);
        result.setTotal(count);
        return result;
    }

    @Override
    public Long doLike(Long id) {
        return null;
    }

    @Override
    public Topic get(Long id) {
        return topicMapper.selectById(id);
    }
}
