package com.wtl.right.util;

import android.util.Log;

/**
 * Function : Logcat调试工具类 如有需要，在项目中将isShow设为true，调用方法。建议正式版将isShow设为false
 * Remarks  :
 * Created by Mr.C on 2018/3/10 0010.
 */

public class LogUtil {

    public static String TAG = "com.wtl.right.util.LogUtil";

    public static boolean isShow = false;

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void a(String msg) {
        a(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }

    public static void a(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }
}
