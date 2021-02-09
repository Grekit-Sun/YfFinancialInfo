package com.yifan.yffinancialinfo.bean.responsebean.base;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data:
 */
public class BaseResult<T> {
    public Integer status;
    public String msg;
    public BaseData<T> result;
}
