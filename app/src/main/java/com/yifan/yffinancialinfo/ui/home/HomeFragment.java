package com.yifan.yffinancialinfo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.base.BaseFragment;
import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.bean.responsebean.home.NewData;
import com.yifan.yffinancialinfo.databinding.FragmentListBinding;
import com.yifan.yffinancialinfo.navinterface.ScrollToTop;
import com.yifan.yffinancialinfo.ui.activity.webview.WebViewActivity;
import com.yifan.yffinancialinfo.ui.adapter.HomeAdapter;

import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-18
 */
public class HomeFragment extends BaseFragment<FragmentListBinding, HomeViewModel> implements ScrollToTop {

    HomeAdapter commonAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        initDataChange();
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected void init() {
        mViewModel.loadHomeData();

        initRecycle();
        initRefreshLayout();
    }

    @Override
    public void scrollToTop() {

    }

    private void initDataChange() {
        mViewModel.getHomeList().observe(this, this::onDataChange);
    }

    private void onDataChange(List<HomeData> homeDataList) {
        commonAdapter.onItemDatasChanged(homeDataList);
        mDataBinding.refreshLayout.finishRefresh();
        mDataBinding.refreshLayout.finishLoadMore();
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycle() {
        commonAdapter = new HomeAdapter() {
            @Override
            public void addListener(View root, HomeData itemData, int position) {
                super.addListener(root, itemData, position);
                if (itemData != null) {

                }
            }

            @Override
            public void addTopClickListener(HomeData itemData) {
                super.addTopClickListener(itemData);
            }

            @Override
            public void addTopCollectListener(HomeData itemData) {
                super.addTopCollectListener(itemData);
            }

            @Override
            public void addNewsItemClickListener(NewData news) {
                super.addNewsItemClickListener(news);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", news.url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        mDataBinding.recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.recycle.setAdapter(commonAdapter);
    }

    private void initRefreshLayout() {
    }
}
