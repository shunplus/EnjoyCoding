package com.example.rxjavademo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xushun on  2019/10/12 10:11.
 * Email：shunplus@163.com
 * Des：
 */
public class DemoService extends IntentService {

    private static final String TAG = "DemoService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DemoService() {
        super("init");
    }


    @Override
    protected void onHandleIntent(@androidx.annotation.Nullable Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(ShunConstant.LAMBDA, TAG + " onCreate");
    }
}
