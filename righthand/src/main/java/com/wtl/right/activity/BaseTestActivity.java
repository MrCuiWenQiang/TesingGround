package com.wtl.right.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.wtl.right.util.DeBugUtil;

/**
 * Function : 周期中的方法调用 便于测试
 * Remarks  :
 * Created by Mr.C on 2018/3/15 0015.
 */

public abstract class BaseTestActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DeBugUtil.showAliveLogcat(TAG, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        DeBugUtil.showAliveLogcat(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        DeBugUtil.showAliveLogcat(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        DeBugUtil.showAliveLogcat(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        DeBugUtil.showAliveLogcat(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DeBugUtil.showAliveLogcat(TAG, "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DeBugUtil.showAliveLogcat(TAG, "onRestart()");
    }
}
