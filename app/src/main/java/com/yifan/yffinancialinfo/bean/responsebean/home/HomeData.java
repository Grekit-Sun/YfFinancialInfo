package com.yifan.yffinancialinfo.bean.responsebean.home;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data:
 */
public class HomeData {

    private BannerData bannerData;
    private YfNews mYfNews;

    public BannerData getBannerData() {
        return bannerData;
    }

    public void setBannerData(BannerData bannerData) {
        this.bannerData = bannerData;
    }

    public YfNews getNewsList() {
        return mYfNews;
    }

    public static class YfNews {

    }

}
