package com.lambad.protectapp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by xushun on  2019/12/9 11:58.
 * Email：shunplus@163.com
 * Des：
 */
public class SiginMain {

    public static void main(String[] args) {
        /**
         * 第3步 打包签名
         */
        File unsignedApk = new File("app/shgbit/1111.apk");
        String path = unsignedApk.getPath();
        System.out.println("path= " + path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
        String date = sdf.format(new Date());
        String signName = path.substring(0, path.length() - 4) + "_sgin_" + date + ".apk";
        File signedApk = new File(signName);
        System.out.println("signedApk path= " + signedApk.getAbsolutePath());
        try {
            Signature.signatureShgbit(unsignedApk, signedApk);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
