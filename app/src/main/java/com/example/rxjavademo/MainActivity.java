package com.example.rxjavademo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.lambad.hookplugin.HookHelperShun;
import com.lambad.hookplugin.TargetActivity;
import com.lambad.rxjavademo.RxTimer;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    RxTimer rxTimer = new RxTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //1.创建一个observable 可被观察的
        Observable<String> observable = Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                emitter.onNext("  test 1");
                emitter.onNext(" test 2");
            }
            emitter.onComplete();

        });

        //2.创建一个Observer  观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("lambda", "onNext : " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("lambda", "onComplete");
            }
        };

        //3.观察者通过订阅（subscribe）被观察者  进行绑定
        observable.subscribe(observer);

        //3秒后执行任务
//        rxTimer.timer(3000, number -> Log.i("lambda","timer= "+number));
        //每间隔4秒后执行任务
//        rxTimer.interval(4000, number -> Log.i("lambda","interval= "+number));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, ShunService.class));
        } else {
            startService(new Intent(this, ShunService.class));
        }


        startService(new Intent(this, DemoService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (rxTimer != null) {
            rxTimer.cancel();
        }
    }

    @OnClick(R.id.targrt)
    public void targrt() {

        try {
//            HookHelper.hookAMS();
//            HookHelper.hookHandler();
//            HookHelper.hookInstrumentation(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HookHelperShun.hookIActivityManager();
        HookHelperShun.hookHandler();
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }
}
