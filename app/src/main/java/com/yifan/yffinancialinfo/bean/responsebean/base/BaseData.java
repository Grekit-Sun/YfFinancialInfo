package com.yifan.yffinancialinfo.bean.responsebean.base;

import com.yifan.yffinancialinfo.bean.responsebean.home.NewData;

import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-02-08
 */
public class BaseData<T> {
    String channel;
    String num;
    List<T> list;
}
