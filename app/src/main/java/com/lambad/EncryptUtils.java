package com.lambad;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;


/**
 * Created by xushun on  2020/1/10 17:46.
 * Email：shunplus@163.com
 * Des：
 */
public class EncryptUtils {
    /**
     * des:
     *
     * @author xushun
     * @time 2020/1/10 17:46
     */
    public static String decodeToString(String str) {

        String result = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                // 1 去掉首尾的 a z
                result = str.substring(1, str.length() - 1);
                //2,将Pdcss 替换为=
                result = result.replace("Pdcss", "=");
                //3,base64  反解码
                result = new String(Base64.decode(str.getBytes("UTF-8"), Base64.DEFAULT));
                ;

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
