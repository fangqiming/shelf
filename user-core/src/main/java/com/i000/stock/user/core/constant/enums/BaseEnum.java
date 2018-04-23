package com.i000.stock.user.core.constant.enums;

/**
 * @Author:qmfang
 * @Description: 所有Enum类的基类
 * @Date:Created in 10:45 2018/4/23
 * @Modified By:
 */
public interface BaseEnum {

    /**
     * 获取码值
     *
     * @return
     */
    Long getCode();

    /**
     * 获取对应的描述信息
     *
     * @return
     */
    String getMsg();

}
