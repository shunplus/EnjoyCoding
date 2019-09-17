package com.lambad.rxjavademo;


import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.lambad.json.JsonBean;
import com.lambad.json.JsonUtil;

/**
 * Created by xushun on  2019/5/13 22:42.
 * Email：shunplus@163.com
 * Des：
 */
public class Main {


    public static void main(String[] args) {
        String base64="12121212";
//       String encode=Base64.encodeToString(base64.getBytes(),Base64.DEFAULT);
//       System.out.println(encode);
//
//       System.out.println(Base64.decode(encode.getBytes(),Base64.DEFAULT));

        textJson();

        String add=null;
        String dkk="rrrr";
        if (dkk.equals(add)){
            System.out.print("  equals  ");
        }else {
            System.out.print("no  equals  ");
        }
    }

    public static void textJson(){
        String json="{\"errorCode\":\"\",\"message\":\"\",\"iserror\":false,\"data\":{\"id\":\"45303010614461710011809\",\"type\":\"home_qz\",\"judgeId\":\"cshish20\",\"createTime\":1560050127028,\"deputyMedia\":[{\"id\":\"76224052711161610025209\",\"name\":\"上传\",\"fid\":\"45303010614461710011809\",\"subtype\":\"1\",\"sort\":1,\"createTime\":1561025911621},{\"id\":\"76224052712482580072900\",\"name\":\"直播\",\"fid\":\"45303010614461710011809\",\"subtype\":\"2\",\"sort\":2,\"createTime\":1561025911621},{\"id\":\"76224052714152410005557\",\"name\":\"拍照\",\"fid\":\"45303010614461710011809\",\"subtype\":\"3\",\"sort\":3,\"createTime\":1561025911621},{\"id\":\"76224052715485040052445\",\"name\":\"录像\",\"fid\":\"45303010614461710011809\",\"subtype\":\"5\",\"sort\":4,\"createTime\":1561025911621},{\"id\":\"7622405271741360041542\",\"name\":\"录音\",\"fid\":\"45303010614461710011809\",\"subtype\":\"4\",\"sort\":5,\"createTime\":1561025911621}]},\"exception\":null}";
        JsonBean bean = null;
        try {
            bean = JsonUtil.parseByGson(json, JsonBean.class);

            Gson gson=new Gson();
            System.out.print(bean.getException());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
