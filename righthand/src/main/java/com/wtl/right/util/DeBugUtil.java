package com.wtl.right.util;

import android.util.Log;

/**
 * Function : Debug 输出工具类    现开启三种模式  网络模式NET    生命周期模式 ALIVE  全部模式 ALL
 * Remarks  :
 * Created by Mr.C on 2018/3/15 0015.
 */

public class DeBugUtil {

    public static boolean ISDEBUG = false;

    public static final int DEBUG_MODEL_ALL = 0X00;
    public static final int DEBUG_MODEL_NET = 0X10;
    public static final int DEBUG_MODEL_ALIVE = 0X20;

    private static int DEBUGMODEL = DEBUG_MODEL_NET;

    //网络模块专用输出
    public static void showNetLogcat(String tag,String message){
        if (isshow(DEBUG_MODEL_NET)){
            Log.e(tag,message);
        }
    }

    //生命周期专用输出
    public static void showAliveLogcat(String tag,String message){
        if (isshow(DEBUG_MODEL_ALIVE)){
            Log.e(tag,message);
        }
    }

    private static boolean isshow(int themodel){
        boolean isshow = false;
        if (ISDEBUG){
            if (DEBUGMODEL == DEBUG_MODEL_ALL){
                isshow = true;
            }else {
                isshow = (themodel==DEBUGMODEL);
            }
        }

        return  isshow;
    }
}
