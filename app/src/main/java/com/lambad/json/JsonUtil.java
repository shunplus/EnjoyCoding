package com.lambad.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xushun on  2019/7/2 10:49.
 * Email：shunplus@163.com
 * Des：
 */
public class JsonUtil {

    public static <T> T parseByGson(String json, Class<T> t) throws Exception {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        Gson gson = gb.create();
        return gson.fromJson(json, t);
    }
}
