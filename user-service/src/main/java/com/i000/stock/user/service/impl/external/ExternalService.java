package com.i000.stock.user.service.impl.external;

import com.alibaba.fastjson.JSONObject;
import com.i000.stock.user.api.entity.bo.IndexBo;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:45 2018/4/25
 * @Modified By:
 */
public interface ExternalService {

    @HTTP(method = "GET", path = "http://hq.sinajs.cn/{list}")
    Call<String> getPrice(@Path("list") String list);

    @HTTP(method = "GET", path = "http://api.shenjian.io/?appid=ad4f4de8853f30da0492f27633a81dfd")
    Call<JSONObject> getIndex();
}
