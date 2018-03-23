package com.wtl.right.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

/**
 * Function : 读取attr 数值的工具类
 * Remarks  :
 * Created by Mr.C on 2018/3/20 0020.
 */

public class ResUtil {

    public static float getAttrFloatValue(Context context,int attRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attRes,typedValue,true);
        return typedValue.getFloat();
    }

    public static int getAttrColor(Context context,int attRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attRes,typedValue,true);
        return typedValue.data;
    }

    public static ColorStateList getAttrColorStateList(Context context,int attRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attRes,typedValue,true);
        return ContextCompat.getColorStateList(context,typedValue.resourceId);
    }

    public static Drawable getAttrDrawable(Context context,int attRes){
        int [] attrs = new int[]{attRes};
        TypedArray typedValue = context.obtainStyledAttributes(attrs);
        Drawable drawable = typedValue.getDrawable(0);
        return drawable;
    }

    public static int getAttrDimen(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes,typedValue,true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, DisplayUtil.getDisplayMetrics(context));
    }
}
