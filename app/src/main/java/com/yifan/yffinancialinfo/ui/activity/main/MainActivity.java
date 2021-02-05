package com.yifan.yffinancialinfo.ui.activity.main;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.base.BaseActivity;
import com.yifan.yffinancialinfo.databinding.ActivityMainBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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
        ButterKnife.bind(this);
        initView();
        initUserData();
    }

    /**
     * 初始化侧边栏和底部状态栏
     */
    private void initView() {
        if (mToolbar == null) {
            return;
        }
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);

    }

    /**
     * 初始化侧边栏用户信息
     */
    private void initUserData() {
    }
}