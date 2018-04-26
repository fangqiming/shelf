package com.i000.stock.user.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:57 2018/4/25
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @TableId
    private Long id;

    private String name;

    private String code;

    private String prefix;

    private String industry;
}
