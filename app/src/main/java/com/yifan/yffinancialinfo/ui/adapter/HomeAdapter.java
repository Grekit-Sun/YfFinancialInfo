package com.yifan.yffinancialinfo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yifan.yffinancialinfo.BR;
import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.bean.responsebean.home.HomeData;
import com.yifan.yffinancialinfo.bean.responsebean.home.NewData;

import java.util.List;

import static com.yifan.yffinancialinfo.config.App.getContext;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-01-18
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeData> mList;

    /**
     * 添加监听回调
     *
     * @param root
     * @param itemData
     * @param position
     */
    public void addListener(View root, HomeData itemData, int position) {
    }

    /**
     * 添加监听回调
     *
     * @param itemData
     */
    public void addTopClickListener(HomeData itemData) {
    }

    /**
     * 添加监听回调
     *
     * @param itemData
     */
    public void addTopCollectListener(HomeData itemData) {
    }

    public void addNewsItemClickListener(NewData news) {
    }

    /**
     * 改变数据
     *
     * @param homeData
     */
    public void onItemDatasChanged(List<HomeData> homeData) {
        this.mList = homeData;
        notifyDataSetChanged();
    }

    public int getItemLayout(HomeData itemData) {
        if (itemData.getBannerData() != null) {
            return R.layout.item_home_banner;
        } else {
            return R.layout.item_home_news;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItemLayout(mList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), viewType, viewGroup, false);
        return new CommonViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder commonViewHolder, int position) {
        HomeData homeData = mList.get(position);

        if (homeData.getNewDatas() != null) {
            //绑定数据
            showNews(((CommonViewHolder) commonViewHolder).binding.getRoot().findViewById(R.id.recycler_view), homeData.getNewDatas());
        }
        addListener(((CommonViewHolder) commonViewHolder).binding.getRoot(), mList.get(position), position);
        //防止数据闪烁
        ((CommonViewHolder) commonViewHolder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    private void showNews(RecyclerView recyclerView, List<NewData> list) {
        CommonAdapter<NewData> commonAdapter = new CommonAdapter<NewData>(list, R.layout.item_news, BR.newsData) {
            @Override
            public void addListener(View root, NewData itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.card_view).setOnClickListener((view) -> {
                    addNewsItemClickListener(itemData);
                });
            }
        };
        recyclerView.setAdapter(commonAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
