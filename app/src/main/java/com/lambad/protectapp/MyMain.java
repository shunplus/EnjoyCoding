package com.lambad.protectapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

/**
 * Created by xushun on  2019/5/13 22:42.
 * Email：shunplus@163.com
 * Des：
 */
public class MyMain {
	public static void main(String[] args) throws Exception {

		/**
		 * 准备工作
		 */
       //存储源核心apk中的解压后的文件
		File tempFileApk = new File("app/source/apk/temp");
		if (tempFileApk.exists()) {
			File[]files = tempFileApk.listFiles();
			for(File file: files){
				if (file.isFile()) {
					file.delete();//先清空文件夹
				}
			}
		}

		//存储壳arr中的解压后的文件
		File tempFileAar = new File("app/source/aar/temp");
		if (tempFileAar.exists()) {
			File[]files = tempFileAar.listFiles();
			for(File file: files){
				if (file.isFile()) {
					file.delete();//先清空文件夹
				}
			}
		}

		/**
		 * 第一步 处理原始apk 加密dex
		 *
		 */
		AES.init(AES.DEFAULT_PWD);
		//这样一个最简单的AES加解密就完成了，
		// 但有一个缺点，密码的长度必须为128位，也就是16个字节，否则会报错；
		//解压apk
		File apkFile = new File("app/source/apk/app-debug.apk");
		File newApkFile = new File(apkFile.getParent() + File.separator + "temp");
		if(!newApkFile.exists()) {
			newApkFile.mkdirs();
		}
		File mainDexFile = AES.encryptAPKFile(apkFile,newApkFile);
		if (newApkFile.isDirectory()) {
			File[] listFiles = newApkFile.listFiles();
			for (File file : listFiles) {
				if (file.isFile()) {
					if (file.getName().endsWith(".dex")) {
						String name = file.getName();
						System.out.println(" name :"+name);
						int cursor = name.indexOf(".dex");
						String newName = file.getParent()+ File.separator + name.substring(0, cursor) + "_" + ".dex";
						System.out.println("newName:"+newName);
						file.renameTo(new File(newName));
					}
				}
			}
		}


		/**
		 * 第二步 处理aar 获得壳dex
		 */
		File aarFile = new File("app/source/aar/protectapp-debug.aar");
		File aarDex  = Dx.jar2Dex(aarFile);
//        aarData = Utils.getBytes(aarDex);   //将dex文件读到byte 数组


		File tempMainDex = new File(newApkFile.getPath() + File.separator + "classes.dex");
		if (!tempMainDex.exists()) {
			tempMainDex.createNewFile();
		}
        System.out.println("MyMain" + tempMainDex.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(tempMainDex);
		byte[] fbytes = Utils.getBytes(aarDex);
		fos.write(fbytes);
		fos.flush();
		fos.close();


		/**
		 * 第3步 打包签名
		 */
		File unsignedApk = new File("app/result/apk-unsigned.apk");
		unsignedApk.getParentFile().mkdirs();
//        File disFile = new File(apkFile.getAbsolutePath() + File.separator+ "temp");
		Zip.zip(newApkFile, unsignedApk);
		//不用插件就不能自动使用原apk的签名...
		File signedApk = new File("app/result/apk-signed.apk");
		Signature.signature(unsignedApk, signedApk);
	}


	private static File getMainDexFile(File apkFile) {
		// TODO Auto-generated method stub
		File disFile = new File(apkFile.getAbsolutePath() + "unzip");
		Zip.unZip(apkFile, disFile);
		File[] files = disFile.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".dex")) {
					return true;
				}
				return false;
			}
		});
		for (File file: files) {
			if (file.getName().endsWith("classes.dex")) {
				return file;
			}
		}
		return null;
	}
}
