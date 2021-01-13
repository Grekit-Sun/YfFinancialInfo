package com.yifan.yffinancialinfo.ui.activity.main;

import androidx.lifecycle.ViewModelProvider;

import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.base.BaseActivity;
import com.yifan.yffinancialinfo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initView();
        initUserData();
    }

    /**
     * 初始化侧边栏和底部状态栏
     */
    private void initView() {}
    /**
     * 初始化侧边栏用户信息
     */
    private void initUserData() {}
}