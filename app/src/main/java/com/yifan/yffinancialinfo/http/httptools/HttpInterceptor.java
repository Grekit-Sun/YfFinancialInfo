package com.yifan.yffinancialinfo.http.httptools;

import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.yifan.yffinancialinfo.bean.responsebean.LoginBean;
import com.yifan.yffinancialinfo.config.Constants;
import com.yifan.yffinancialinfo.util.HttpException;
import com.yifan.yffinancialinfo.util.NetworkUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 自定义请求拦截器
 *
 * @author devel
 */
public class HttpInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static String REQUEST_TAG = "请求";

    private static final String LINE = "============================================================================================================================================================================";

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isConnected()) {
            throw new HttpException("网络连接异常，请检查网络后重试");
        }
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            Hawk.put(Constants.HawkCode.COOKIE, cookies);
        }
        Request request = chain.request();
        request = getHeaderRequest(request);
        logRequest(request);
        Response response = chain.proceed(request);
        logResponse(response);
        return originalResponse;
    }

    /**
     * 添加header
     */
    public Request getHeaderRequest(Request request) {
        LoginBean loginData = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        Request headRequest;
        if (loginData != null) {
            headRequest = request
                    .newBuilder()
                    .addHeader("token", loginData.getToken())
                    .addHeader("Cookie", "loginUserName=" + loginData.getUsername())
                    .addHeader("Cookie", "loginUserPassword=" + loginData.getPassword())
                    .build();
        } else {
            headRequest = request
                    .newBuilder()
                    .addHeader("platform", "android")
                    .addHeader("version", "1.0")
                    .build();
        }
        return headRequest;
    }

    /**
     * 打印请求信息
     *
     * @param request
     */
    private void logRequest(Request request) {
        Log.d("Request Log Start",LINE);
        Log.d("Request Log Start",LINE);
        Log.d(REQUEST_TAG + "method", request.method());
        Log.d(REQUEST_TAG + "url", request.url().toString());
        Log.d(REQUEST_TAG + "header", request.headers().toString().replace("\n","  "));
        if (request.method().equals("GET")) {
            Log.d("Request Log Stop",LINE);
            Log.d("Request Log Stop",LINE);
            return;
        }
        try {
            RequestBody requestBody = request.body();
            String parameter = null;
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            parameter = buffer.readString(UTF8);
            buffer.flush();
            buffer.close();
            Log.d(REQUEST_TAG + "参数", parameter);
            Log.d("Request Log Stop",LINE);
            Log.d("Request Log Stop",LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印返回结果
     *
     * @param response
     */
    private void logResponse(Response response) {
        try {
            ResponseBody responseBody = response.body();
            String rBody = null;
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
            Log.d("Response Log Stop",LINE);
            Log.d("Response Log Stop",LINE);
            Log.d(REQUEST_TAG + "返回值", rBody);
            Log.d("Response Log Stop",LINE);
            Log.d("Response Log Stop",LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

