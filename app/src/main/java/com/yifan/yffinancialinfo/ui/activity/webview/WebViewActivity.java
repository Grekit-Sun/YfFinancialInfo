package com.yifan.yffinancialinfo.ui.activity.webview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yifan.yffinancialinfo.R;
import com.yifan.yffinancialinfo.base.BaseActivity;
import com.yifan.yffinancialinfo.databinding.ActivityWebviewBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-02-09
 */
public class WebViewActivity extends BaseActivity<ActivityWebviewBinding,WebViewModel> {

    @BindView(R.id.wv_news)
    WebView mWebview;
    private String url;

    private WebChromeClient mWebChromeClient;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        adaptWebView();
        initWebChromClient();
        loadWeb();
    }

    private void adaptWebView(){
        //支持javascript
        mWebview.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebview.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mWebview.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        mWebview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebview.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
    }

    private void loadWeb(){
        //避免弹出浏览器
        mWebview.setWebViewClient(new WebViewClient(){

            //重定向URL请求，返回true表示拦截此url，返回false表示不拦截此url。
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //作用1：重定向url
                if(url.startsWith("weixin://")){
                    url = url.replace("weixin://","http://");
                    mWebview.loadUrl(url);
                }
                //作用2：在本页的webview打开，防止外部浏览器打开此链接
                view.loadUrl(url);
                return true;
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        url = extras.getString("url");

        //方式一：加载一个网页
        mWebview.loadUrl(url);
    }

    private void initWebChromClient() {
        mWebChromeClient = new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        };
    }
}
