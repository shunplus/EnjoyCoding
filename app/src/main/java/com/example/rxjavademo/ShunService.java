package com.example.rxjavademo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lambad.rxjavademo.RxTimer;

/**
 * Created by xushun on  2019/10/11 09:21.
 * Email：shunplus@163.com
 * Des： adil service
 */
public class ShunService extends Service {


    NotificationManager notificationManager;
    String notificationId = "channelId";
    String notificationName = "channelName";
    private Bean bean;
    private IBinder iBinder = new IMessageAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public Bean get() throws RemoteException {
            return bean;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundService();
        Log.e("lambda", "onCreate: success");
        bean = new Bean();
        RxTimer rxTimer = new RxTimer();
        rxTimer.interval(1000, new RxTimer.IRxNext() {
            @Override
            public void doNext(long number) {
                bean.setId((int) number);
            }
        });

    }

    private void startForegroundService() {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //创建NotificationChannel

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(notificationId, notificationName, NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);

        }

        startForeground(1, getNotification());

    }

    private Notification getNotification() {

        Notification.Builder builder = new Notification.Builder(this)

                .setSmallIcon(R.drawable.ic_launcher_background)

                .setContentTitle("投屏服务")

                .setContentText("投屏服务正在运行...");

        //设置Notification的ChannelID,否则不能正常显示

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setChannelId(notificationId);

        }

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        return notification;

    }
}
