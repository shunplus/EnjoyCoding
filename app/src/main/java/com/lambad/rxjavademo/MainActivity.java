package com.lambad.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    RxTimer rxTimer=new RxTimer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.创建一个observable 可被观察的
        Observable<String> observable=Observable.create(emitter -> {
            if (!emitter.isDisposed()){
               emitter.onNext("  test 1");
               emitter.onNext(" test 2");
            }
            emitter.onComplete();

        });

        //2.创建一个Observer  观察者
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
            Log.i("lambda","onNext : "+s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("lambda","onComplete");
            }
        };

        //3.观察者通过订阅（subscribe）被观察者  进行绑定
        observable.subscribe(observer);

        //3秒后执行任务
        rxTimer.timer(3000, number -> Log.i("lambda","timer= "+number));
        //每间隔4秒后执行任务
        rxTimer.interval(4000, number -> Log.i("lambda","interval= "+number));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (rxTimer != null) {
            rxTimer.cancel();
        }
    }
}
