package com.baize.unity_aar.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;


/**
 * Created by lxb on 2017/2/8.
 */

public class PageSwitchUtils {

    /**
     * 进行页面跳转,可以携带多个参数，参数用数组进行封装
     *
     * @param clzz
     */
    public static void GoActivityByParams(Activity context, Class<?> clzz, String[] keys, String[] values) {
        Intent intent = new Intent(context, clzz);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                if (!TextUtils.isEmpty(keys[i]) && !TextUtils.isEmpty(values[i])) {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        context.startActivity(intent);
    }


    /**
     * 跳转时不带参数
     *
     * @param context
     * @param clzz
     */
    public static void GoActivityNoParams(Activity context, Class<?> clzz) {
        GoActivityByParams(context, clzz, null, null);
    }
}
