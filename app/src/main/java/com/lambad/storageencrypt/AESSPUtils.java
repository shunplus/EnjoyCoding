package com.lambad.storageencrypt;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xushun on  2019/11/1 11:50.
 * Email：shunplus@163.com
 * Des：
 */
public class AESSPUtils {
    private static final String file_name = "file_new_name";
    private static AES256SharedPreferences mAES256SharedPreferences;


    /**
     * des:
     *
     * @author xushun
     * @time 2019/11/1 14:25
     */
    public static void putString(String key, String value) {
        if (mAES256SharedPreferences == null) {
            mAES256SharedPreferences = new AES256SharedPreferences(AppUtils.getContext().getSharedPreferences(file_name, Context.MODE_PRIVATE));
        }
        SharedPreferences.Editor editor = mAES256SharedPreferences.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * des:
     *
     * @author xushun
     * @time 2019/11/1 14:25
     */
    public static void getString(String key, String defvalue) {
        if (mAES256SharedPreferences == null) {
            mAES256SharedPreferences = new AES256SharedPreferences(AppUtils.getContext().getSharedPreferences(file_name, Context.MODE_PRIVATE));
        }
        mAES256SharedPreferences.getString(key, defvalue);

    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     * 里面所有的commit操作使用了SharedPreferencesCompat.apply进行了替代，目的是尽可能的使用apply代替commit
     * 首先说下为什么，因为commit方法是同步的，并且我们很多时候的commit操作都是UI线程中，毕竟是IO操作，尽可能异步；
     * 所以我们使用apply进行替代，apply异步的进行写入；
     * 由于在apply是在API 9以后引入，所以有了这个兼容；
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
