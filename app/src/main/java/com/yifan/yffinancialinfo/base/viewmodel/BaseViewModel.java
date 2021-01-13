package com.yifan.yffinancialinfo.base.viewmodel;

import android.content.res.Resources;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.config.App;
import com.yifan.yffinancialinfo.config.LoadState;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-13
 */
public abstract class BaseViewModel extends ViewModel implements DefaultLifecycleObserver {

    public Resources resources;

    /**
     * 加载状态
     */
    public MutableLiveData<LoadState> loadState = new MutableLiveData<>();

    public MutableLiveData<String> errorMsg = new MutableLiveData<>(getResources().getString(R.string.load_error));

    /**
     * 是否为刷新数据
     */
    public boolean mRefresh;

    /**
     * 重新加载数据。没有网络，点击重试时回调
     */
    public void reloadData() {

    }

    public Resources getResources() {
        if (resources == null) {
            resources = App.getContext().getResources();
        }
        return resources;
    }
}
