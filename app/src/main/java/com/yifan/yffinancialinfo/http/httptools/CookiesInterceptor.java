package com.yifan.yffinancialinfo.http.httptools;

import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.yifan.yffinancialinfo.config.Constants;
import com.yifan.yffinancialinfo.util.HttpException;
import com.yifan.yffinancialinfo.util.NetworkUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (!NetworkUtils.isConnected()) {
            throw new HttpException("网络连接异常，请检查网络后重试");
        }
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = Hawk.get(Constants.HawkCode.COOKIE);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie);
                // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}