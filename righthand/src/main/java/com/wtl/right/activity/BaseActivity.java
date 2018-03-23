package com.wtl.right.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Function : 基本的Activity 规范各个方法
 * Remarks  :
 * Created by Mr.C on 2018/3/10 0010.
 */

public abstract class BaseActivity extends BaseTestActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != -1) {
            if (!ifInitContentview()) {
                setContentView(getLayoutId());
            } else {
                initContentview();
            }
        }
        initActionBar();
        initView();
        initData(savedInstanceState);
        initListener();
    }

    //初始化视图
    protected abstract void initView();

    //标题栏
    protected void initActionBar() {

    }

    //初始化数据
    protected abstract void initData(Bundle savedInstanceState);

    //初始化监听
    protected void initListener() {
    }

    //添加根视图
    protected abstract int getLayoutId();

    //是否将设置布局转移给下面 做自定义实现 true 是
    protected boolean ifInitContentview() {
        return false;
    }

    //是否将设置布局转移给下面 做自定义实现
    protected void initContentview() {
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
