package com.yifan.yffinancialinfo.http.request;

import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.bean.responsebean.home.NewsData;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
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
    Flowable<List<NewsData>> getHomeData(@QueryMap Map<String, Object> map);
}
