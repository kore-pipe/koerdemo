package com.example.chapter7_client.Util;

import android.content.Context;

public class Utils {

    /**
     * 将dp值转换为px值，保证尺寸大小与屏幕密度无关
     *
     * @param context Context对象
     * @param dpValue 需要转换的dp值
     * @return 转换后的px值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dp值，保证尺寸大小与屏幕密度无关
     *
     * @param context Context对象
     * @param pxValue 需要转换的px值
     * @return 转换后的dp值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}