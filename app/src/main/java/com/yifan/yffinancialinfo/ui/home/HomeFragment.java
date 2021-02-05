package com.yifan.yffinancialinfo.ui.home;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.base.BaseFragment;
import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.databinding.FragmentListBinding;
import com.yifan.yffinancialinfo.navinterface.ScrollToTop;
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
    }

    @Override
    public void scrollToTop() {

    }

    private void initDataChange() {
        mViewModel.getHomeList().observe(this, new Observer<List<HomeData>>() {
            @Override
            public void onChanged(List<HomeData> homeDataList) {
                commonAdapter.onItemDatasChanged(homeDataList);
                mDataBinding.refreshLayout.finishRefresh();
                mDataBinding.refreshLayout.finishLoadMore();
            }
        });
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycle() {
        commonAdapter = new HomeAdapter() {
            @Override
            public void addListener(View root, HomeData itemData, int position) {
                super.addListener(root, itemData, position);
            }

            @Override
            public void addTopClickListener(HomeData itemData) {
                super.addTopClickListener(itemData);
            }

            @Override
            public void addTopCollectListener(HomeData itemData) {
                super.addTopCollectListener(itemData);
            }
        };
    }
}
