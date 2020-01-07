package com.example.rxjavademo;

import android.app.Application;
import android.content.Context;

/**
 * Created by xushun on  2019/10/20 14:08.
 * Email：shunplus@163.com
 * Des：
 */
public class ShunApplication extends Application {

    static Context mContext;

    /**
     * des:
     *
     * @author xushun
     * @time 2019/11/1 11:57
     */
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
