package com.lambad.network;

import android.annotation.SuppressLint;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by xushun on 2019/4/19.
 * Des:
 * Email:shunplus@163.com
 */

public class RetrofitHttp {

    private static final int DEFAULT_TIMEOUT = 10;
    private static final String TAG = "RetrofitClient";
    private static RetrofitHttp sIsntance;
    private RxRestService apiService;
    private OkHttpClient okHttpClient;
    // 清除线程需要用到的
    private Disposable disposable;

    private RetrofitHttp(String baseUrl) {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                //信任所有证书
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .sslSocketFactory(SSLUtil.createSSLSocketFactory())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        apiService = retrofit.create(RxRestService.class);
    }

    public static RetrofitHttp getInstance(String baseUrl) {
        if (sIsntance == null) {
            synchronized (RetrofitHttp.class) {
                if (sIsntance == null) {
                    sIsntance = new RetrofitHttp(baseUrl);
                }
            }
        }
        return sIsntance;
    }


    @SuppressLint("CheckResult")
    public void post(String url, WeakHashMap<String, Object> hashMap, Observer<String> observer) {
        apiService.post(url, hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @SuppressLint("CheckResult")
    public void postBean(String url, WeakHashMap<String, Object> hashMap, Observer<Bean> observer) {
        apiService.postBean(url, hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 销毁
     */
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}