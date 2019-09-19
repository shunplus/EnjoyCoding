package com.lambad.protectapp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xushun on  2019/5/13 22:42.
 * Email：shunplus@163.com
 * Des：
 */

public class Signature {
    public static void signature(File unsignedApk, File signedApk) throws InterruptedException, IOException {
        String cmd[] = {"cmd.exe", "/C ","jarsigner",  "-sigalg", "MD5withRSA",
                "-digestalg", "SHA1",
                "-keystore", "D:\\Documents\\ReinforceApk\\app\\shunplus.jks",
                "-storepass", "123456",
                "-keypass", "123456",
                "-signedjar", signedApk.getAbsolutePath(),
                unsignedApk.getAbsolutePath(),
                "shun"};
        Process process = Runtime.getRuntime().exec(cmd);
        System.out.println("start sign");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null)
//            System.out.println("tasklist: " + line);
        try {
            int waitResult = process.waitFor();
            System.out.println("waitResult: " + waitResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println("process.exitValue() " + process.exitValue() );
        if (process.exitValue() != 0) {
            InputStream inputStream = process.getErrorStream();
            int len;
            byte[] buffer = new byte[2048];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while((len=inputStream.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            System.out.println(new String(bos.toByteArray(),"GBK"));
            throw new RuntimeException("签名执行失败");
        }
        System.out.println("finish signed");
        process.destroy();
    }
}
