package com.example.rxjavademo;

import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xushun on  2020/4/7 18:09.
 * Email：shunplus@163.com
 * Des：
 */
public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_v)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.web_activity_layout);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initWeb();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initWeb() {
        WebSettings settings = webView.getSettings();
        // 设置WebView支持JavaScript
        settings.setJavaScriptEnabled(true);
        //支持自动适配
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);  //支持放大缩小
        settings.setBuiltInZoomControls(true); //显示缩放按钮
        settings.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setSaveFormData(true);
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);/// 支持通过JS打开新窗口
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置不让其跳转浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        // 添加客户端支持
        webView.setWebChromeClient(new WebChromeClient());
        // mWebView.loadUrl(TEXTURL);

        //不加这个图片显示不出来
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setBlockNetworkImage(false);

        //允许cookie 不然有的网站无法登陆
        CookieManager mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
        mCookieManager.setAcceptThirdPartyCookies(webView, true);

        webView.loadUrl("http://tinymce.ax-z.cn/demos/demo-classic.php");
    }

}
