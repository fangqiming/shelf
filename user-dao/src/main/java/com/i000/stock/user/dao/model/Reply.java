package com.i000.stock.user.dao.model;

import com.i000.stock.user.dao.model.base.BaseModel;
import lombok.Data;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:23 2018/4/24
 * @Modified By:
 */
@Data
public class Reply extends BaseModel {

    private Long topicId;

    private String userCode;

    private String content;

    private Long replyId;

    private Long goodNum;

    private Long badNum;
}
