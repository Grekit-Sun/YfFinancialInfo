package com.yifan.yffinancialinfo.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yifan.yffinancialinfo.base.viewmodel.BaseViewModel;
import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.bean.responsebean.home.NewsData;
import com.yifan.yffinancialinfo.config.Constants;
import com.yifan.yffinancialinfo.config.LoadState;
import com.yifan.yffinancialinfo.http.data.HttpDisposable;
import com.yifan.yffinancialinfo.http.request.HttpFactory;
import com.yifan.yffinancialinfo.http.request.HttpRequest;
import com.yifan.yffinancialinfo.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-18
 */
public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<List<HomeData>> mHomeList;
    private List<HomeData> mList;
    private static final String URL_WY_JD = "https://way.jd.com";

    /**
     * 请求页码
     */
    private int mPageNum;

    public HomeViewModel() {
        Log.e("生命周期", "ViewModel初始化");
        mHomeList = new MutableLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<List<HomeData>> getHomeList() {
        return mHomeList;
    }

    /**
     * 获取首页数据
     */
    public void loadHomeData() {
        if (!mRefresh) {
            loadState.setValue(LoadState.LOADING);
        }
        mPageNum = 0;
        loadBanner();
    }

    /**
     * 获取首页轮播图
     */
    private void loadBanner() {

        if (NetworkUtils.isConnected() && NetworkUtils.getWifiEnabled()) {
            loadBannerByNet();
            loadNewsByNet();
        } else {
            loadBannerByDb();
        }
    }

    /**
     * 从网络接口获取Banner
     */
    private void loadBannerByNet() {

    }

    /**
     * 从网络接口获取Banner
     */
    private void loadBannerByDb() {
    }


    /**
     * 从网络接口获取news
     */
    private void loadNewsByNet() {
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", Constants.News.APP_KEY);
        map.put("channel", "头条");
        map.put("num", 10);
        map.put("start", 0);
        HttpRequest.getInstance(URL_WY_JD)
                .getHomeData(map)
                .compose(HttpFactory.Flowableschedulers())
                .subscribe(new Consumer<List<NewsData>>() {
                    @Override
                    public void accept(List<NewsData> newsData) throws Exception {
                        Log.d("RXjava:", newsData.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("RXjava:", "onError:" + throwable.toString());
                    }
                });
    }
}
