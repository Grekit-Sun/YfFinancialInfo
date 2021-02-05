package com.yifan.yffinancialinfo.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yifan.yffinancialinfo.base.viewmodel.BaseViewModel;
import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.config.LoadState;
import com.yifan.yffinancialinfo.util.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-18
 */
public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<List<HomeData>> mHomeList;
    private List<HomeData> mList;

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
        } else {
            loadBannerByDb();
        }
    }

    /**
     * 从网络接口获取Banner
     */
    private void loadBannerByNet() {
    }
}
