package com.wtl.right.activity;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtl.right.R;

/**
 * Function : 带有标题栏的Activity
 * Remarks  :
 * Created by Mr.C on 2018/3/10 0010.
 */

public abstract class BaseToolBarActivity extends BaseJobActivity {

    private TextView tv_title;
    private TextView tv_subtitle;
    private Toolbar toolbar;

    private LinearLayout parentLinearLayout;

    protected final void initActionBar() {
        tv_title = findViewById(R.id.tv_title);
        tv_subtitle = findViewById(R.id.tv_subtitle);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private final void initGroup(@LayoutRes int layoutId) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutId, parentLinearLayout, true);
    }

    @Override
    protected boolean ifInitContentview() {
        return true;
    }

    @Override
    protected void initContentview() {
        if (getLayoutId() != -1) {
            initGroup(R.layout.ac_basetoolbar);
            setContentView(getLayoutId());
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);

    }


    /**
     * 设置居中标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        tv_title.setText(title);
    }

    /**
     * 设置居中标题的大小
     *
     * @param size
     */
    protected void setTitleSize(float size) {
        tv_title.setTextSize(size);
    }
    /**
     * 设置居中标题的颜色
     *
     * @param color
     */
    public void setTitleColor(@ColorInt int color) {
        tv_title.setTextColor(color);
    }
    /**
     * 设置副标题
     *
     * @param title
     */
    protected void setSubTitle(String title) {
        tv_subtitle.setText(title);
    }

    /**
     * 设置副标题的大小
     *
     * @param size
     */
    protected void setSubTitleSize(float size) {
        tv_subtitle.setTextSize(size);
    }
    /**
     * 设置副标题的颜色
     *
     * @param color
     */
    public void setSubTitleColor(@ColorInt int color) {
        tv_subtitle.setTextColor(color);
    }
    /**
     * 设置toolbar中的标题  但位置只能在左面 无法居中
     * BUG:不显示
     *
     * @param title
     */
    protected void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    /**
     * 设置toolbar中的副标题  但位置只能在左面 无法居中
     *
     * @param title
     */
    protected void setToolbarSubTitle(String title) {
        toolbar.setSubtitle(title);
    }

    protected void setLogo(@DrawableRes int resId) {
        toolbar.setLogo(resId);
    }

    protected void setBackIcon(@DrawableRes int resId) {
        toolbar.setNavigationIcon(resId);
        setBackButton();
    }

    private final void setBackButton() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
                finish();
            }
        });
    }

    /**
     * 用户点击了标题栏返回触发
     */
    protected void onBackClick() {

    }

}
