package com.yifan.yffinancialinfo.http.request;

import com.yifan.yffinancialinfo.bean.responsebean.base.BaseBackMsg;
import com.yifan.yffinancialinfo.bean.responsebean.home.NewData;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-02-07
 */
public interface ApiAddress {

    /**
     * 获取首页banner
     *
     * @return
     */
    @GET("jisuapi/get")
    Flowable<BaseBackMsg<NewData>> getHomeData(@QueryMap Map<String, Object> map);
}
