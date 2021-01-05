package com.example.rxjavademo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.lambad.hookplugin.HookHelperShun;
import com.lambad.hookplugin.TargetActivity;
import com.lambad.network.Bean;
import com.lambad.network.RetrofitHttp;
import com.lambad.protectapp.Signature;
import com.lambad.rxjavademo.RxTimer;
import com.lambad.sideslide.SideSlideActivity;
import com.lambad.storageencrypt.AESSPUtils;
import com.lambad.storageencrypt.SpUtils;
import com.lambad.util.RsaUtils;
import com.lambad.util.aessp.PLog;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    RxTimer rxTimer = new RxTimer();

    public static String decodeToString(String str) {

        String result = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                // 1 去掉首尾的 a z
                result = str.substring(1, str.length() - 1);
                Log.e(ShunConstant.LAMBDA, result);
                //2,将Pdcss 替换为=
                result = result.replace("Pdcss", "=");
                Log.e(ShunConstant.LAMBDA, result);
                //3,base64  反解码
//                result = new String(Base64.decode(str.getBytes(), Base64.DEFAULT));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    result = new String(Base64.getUrlDecoder().decode(result));
                }
                Log.e(ShunConstant.LAMBDA, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "0");
            jsonObject.put("zybm", "10001");

            String encryptSresult = RsaUtils.encrypt(jsonObject.toString(), RsaUtils.getPublicKey(RsaUtils.publicKey));
            PLog.i(ShunConstant.LAMBDA, "result =" + encryptSresult);

            encryptSresult = replaceBlank(encryptSresult);
            PLog.i(ShunConstant.LAMBDA, "result replaceBlank=" + encryptSresult);
            String urlStr = URLEncoder.encode(encryptSresult, "UTF-8");
            PLog.i(ShunConstant.LAMBDA, "encodeStr=" + urlStr);
            String decodeStr = URLDecoder.decode(urlStr, "UTF-8");
            PLog.i(ShunConstant.LAMBDA, "decodeStr=" + decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stMd5 = ShunUtils.getSignMd5Str(this);

        Log.d(ShunConstant.LAMBDA, stMd5);

        try {
            Signature.signature22(new File(""), new File(""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String text = "TPFWaPJ/7y/c6+mdoBAdBfO3OplRnPOk7qKORjRGbDCW1sRXqW+iH3KCbE8NJ4mdPmItdBcwpmYB\n" +
                "FcQEYnVWj9oqfxScJQherXmVg34ijKKCQ+hQVLjudAdyR2r3bHJUq+AMEF7kQ3WFVoGz6vJ0u2Cv\n" +
                "MUcxFIogydZdYtt/pV4=";


        try {
//            String entryStr = RsaUtils.encrypt("{\\n\" +\n" +
//                    "           \"    \\\"type\\\": 1,\\n\" +\n" +
//                    "           \"    \\\"zybm\\\": 10001\\n\" +\n" +
//                    "           \"}", RsaUtils.getPublicKey(RsaUtils.publicKey));
//            PLog.e(ShunConstant.LAMBDA, " entryStr = " + entryStr);
//            entryStr = replaceBlank(entryStr);
//            PLog.e(ShunConstant.LAMBDA, " entryStr = " + entryStr);
//            PLog.outPutLog(entryStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    @OnClick(R.id.slide)
    public void slide() {
        startActivity(new Intent(this, SideSlideActivity.class));
    }

    @OnClick(R.id.tv_aes)
    public void tv_aes() {


        SpUtils.putString("xushun", "12131313");
        Log.d(ShunConstant.LAMBDA, SpUtils.getString("xushun", "77777"));

        AESSPUtils.putString("xushunwww", "ewewew");
        Log.d(ShunConstant.LAMBDA, SpUtils.getString("xushunwww", "8888"));

    }

    @OnClick(R.id.tv_base64)
    public void tv_base64() {
        Log.e(ShunConstant.LAMBDA, decodeToString("aMjAwMDAwMTQwNzE0MDAxMAPdcssPdcssz"));

//        RetrofitHttp.getInstance("https://gistest.shgbitcloud.com").post("/iexe/a/sys/office/getCourtList", new WeakHashMap<>(), new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(ShunConstant.LAMBDA, "s=" + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(ShunConstant.LAMBDA, "onError =" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        RetrofitHttp.getInstance("https://gistest.shgbitcloud.com").postBean("/iexe/a/sys/office/getCourtList", new WeakHashMap<>(), new Observer<Bean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bean s) {
                Log.d(ShunConstant.LAMBDA, "s=" + s.getData().get(0).getId());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(ShunConstant.LAMBDA, "onError =" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });


    }

    @OnClick(R.id.web_v)
    public void web() {

        startActivity(new Intent(this, WebViewActivity.class));
    }
}
