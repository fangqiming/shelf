package com.i000.stock.user.service.impl;

import com.i000.stock.user.api.entity.bo.IndexBo;
import com.i000.stock.user.api.service.IndexService;
import com.i000.stock.user.dao.mapper.IndexInfoMapper;
import com.i000.stock.user.dao.model.IndexInfo;
import com.i000.stock.user.service.impl.external.ExternalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:16 2018/4/25
 * @Modified By:
 */
@Slf4j
@Component
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ExternalServiceImpl externalService;

    @Resource
    private IndexInfoMapper indexInfoMapper;

    @Override
    public List<IndexInfo> get() {
        IndexBo index = externalService.getIndex();
        if (!Objects.isNull(index)) {
            return index.getData();
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<IndexInfo> get(String date) {
        return indexInfoMapper.find(date);
    }

    @Override
    public void save() {
        List<IndexInfo> indices = get();
        if (!CollectionUtils.isEmpty(indices)) {
            if (indexInfoMapper.findCount(indices.get(0).getDate()) > 0) {
                return;
            }
            for (IndexInfo indexInfo : indices) {
                indexInfoMapper.insert(indexInfo);
            }
        }
    }
}
