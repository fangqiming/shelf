package com.i000.stock.user.dao.model;

import com.i000.stock.user.dao.model.base.BaseModel;
import lombok.Data;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:20 2018/4/24
 * @Modified By:
 */
@Data
public class Topic extends BaseModel {

    private String content;

    private String title;

    private String userCode;

    private Integer clickNum;

}
