package com.yifan.yffinancialinfo.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yifan.yffinancialinfo.base.viewmodel.BaseViewModel;
import com.yifan.yffinancialinfo.bean.responsebean.base.NewsMsg;
import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.bean.responsebean.home.NewData;
import com.yifan.yffinancialinfo.config.Constants;
import com.yifan.yffinancialinfo.config.LoadState;
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

    private static final String TAG = "HomeViewModel";

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
//            loadBannerByNet();
            //加载资讯数据
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
        map.put("num", 100);
        map.put("start", 0);
        HttpRequest.getInstance(URL_WY_JD)
                .getHomeData(map)
                .compose(HttpFactory.Flowableschedulers())
                .subscribe(new Consumer<NewsMsg<NewData>>() {
                    @Override
                    public void accept(NewsMsg<NewData> newsMsg) throws Exception {
                        HomeData homeData = new HomeData();
                        homeData.setNewDatas(newsMsg.result.result.list);
                        mList.add(homeData);
                        mHomeList.postValue(mList);
                        Log.d(TAG, "JD Cloud News Receive Success!");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "JD Cloud News Receive Failed...");
                    }
                });
    }
}
