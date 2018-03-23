package com.wtl.right.activity;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

/**
 * Function : 在此处放入常用方法
 * Remarks  :
 * Created by Mr.C on 2018/3/10 0010.
 */

public abstract class BaseJobActivity extends BaseActivity {

    //获取String
    protected String getStringMethod(@StringRes int id){
        return this.getResources().getString(id);
    }

    //获取颜色值 getResources().getColor()方法过时 所以使用ContextCompat 兼容方法
    protected int getColorMethod(@ColorRes int id){
        return ContextCompat.getColor(this,id);
    }


}
