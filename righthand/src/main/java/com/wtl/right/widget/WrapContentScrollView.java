package com.wtl.right.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/3/21 0021.
 */

public class WrapContentScrollView extends ScrollView {

    private int mMaxHeight = Integer.MAX_VALUE >> 2;

    public WrapContentScrollView(Context context) {
        super(context);
    }

    public WrapContentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapContentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WrapContentScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMaxHeight(int maxHeight){
        if (mMaxHeight != maxHeight) {
            mMaxHeight = maxHeight;
           requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(mMaxHeight,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
