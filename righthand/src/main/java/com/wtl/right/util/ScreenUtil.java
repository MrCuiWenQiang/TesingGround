package com.wtl.right.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.WindowManager;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/3/14 0014.
 */

public class ScreenUtil {
    /**
     * 检测应用是否是竖屏
     *
     * @return
     */
    public static boolean isCreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /***
     * 获取屏幕高度（不包含状态栏）
     *
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context) {
        /**可用屏幕高度*/
        int height = 0;
        /**状态栏高度*/
        int statusBarHeight = 0;
        Activity ac = (Activity) context;
        WindowManager wm = (WindowManager) ac
                .getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = ScreenUtil.getPixelSize(context, resourceId);
        }
        height = height - statusBarHeight;
        return height;
    }

    /***
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getMaxWindowHeight(Context context) {
        /**可用屏幕高度*/
        int height = 0;
        /**状态栏高度*/
        int statusBarHeight = 0;
        Activity ac = (Activity) context;
        WindowManager wm = (WindowManager) ac
                .getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();

        return height;
    }

    /***
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        int width = 0;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /***
     * 获取屏幕中的大小
     *
     * @param context 关联
     * @param sizeId  大小的id
     * @return 大小
     */
    public static int getPixelSize(Context context, int sizeId) {
        int size = 0;
        if (sizeId != -1) {
            size = context.getResources().getDimensionPixelSize(sizeId);
        }
        return size;
    }
}
