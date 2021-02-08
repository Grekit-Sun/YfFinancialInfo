package com.yifan.yffinancialinfo.bean.responsebean.base;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data:
 */
public class BaseResult<T> {
    Integer status;
    String msg;
    BaseData<T> result;
}
