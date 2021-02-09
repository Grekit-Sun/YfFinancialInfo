package com.yifan.yffinancialinfo.bean.responsebean.home;

import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data:
 */
public class HomeData {

    private BannerData bannerData;
    private List<NewData> newDatas;

    public List<NewData> getNewDatas() {
        return newDatas;
    }

    public void setNewDatas(List<NewData> newDatas) {
        this.newDatas = newDatas;
    }

    public BannerData getBannerData() {
        return bannerData;
    }

    public void setBannerData(BannerData bannerData) {
        this.bannerData = bannerData;
    }
}
