package com.yifan.yffinancialinfo.bean.responsebean.base;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-02-08
 */
public class BaseBackMsg<T> {
    public String code;
    public String charge;
    public String msg;
    public BaseResult<T> result;
}
