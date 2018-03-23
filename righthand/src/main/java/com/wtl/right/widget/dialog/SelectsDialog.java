package com.wtl.right.widget.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wtl.right.R;

/**
 * Function : 带有两个选项的对话框 默认是确定 取消
 * Remarks  :
 * Created by Mr.C on 2018/3/14 0014.
 */

public class SelectsDialog extends BaseDialog implements View.OnClickListener {

    private TextView tv_title; //标题
    private TextView tv_content; //内容

    private TextView bt_left; //左按钮
    private TextView bt_right;//右按钮
    private View v_div;

    private String title;
    private String content;

    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_CONTENT = "KEY_CONTENT";

    private OnSelectListener onSelectListener;

    public SelectsDialog() {
    }

    public static SelectsDialog newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(KEY_CONTENT, content);
        SelectsDialog fragment = new SelectsDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static SelectsDialog newInstance(String title, String content) {
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_CONTENT, content);
        SelectsDialog fragment = new SelectsDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dg_selects;
    }

    @Override
    public void initview(View v) {
        tv_title = v.findViewById(R.id.tv_title);
        tv_content = v.findViewById(R.id.tv_content);
        bt_left = v.findViewById(R.id.bt_left);
        bt_right = v.findViewById(R.id.bt_right);
        v_div = v.findViewById(R.id.v_div);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle!=null){
            title = bundle.getString(KEY_TITLE);
            content = bundle.getString(KEY_CONTENT);
        }
        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        } else {
            goneTitle();
        }
        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);
        }
    }

    public void setTitle(String title) {
        if (isAdded()) {
            tv_title.setText(title);
            visibleTitle();
        } else {
            this.title = title;
        }
    }

    public void setContent(String content) {
        if (isAdded()) {
            tv_content.setText(content);
        } else {
            this.content = content;
        }
    }

    @Override
    public void initListener() {
        bt_left.setOnClickListener(this);
        bt_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_left) {
            dismiss();
            if (onSelectListener != null) {
                onSelectListener.leftonclick();
            }
        } else if (i == R.id.bt_right) {
            dismiss();
            if (onSelectListener != null) {
                onSelectListener.rightonclick();
            }
        }
    }

    private void goneTitle() {
        v_div.setVisibility(View.GONE);
        tv_title.setVisibility(View.GONE);
    }

    private void visibleTitle() {
        if (v_div.getVisibility() != View.VISIBLE) {
            v_div.setVisibility(View.VISIBLE);
            tv_title.setVisibility(View.VISIBLE);
        }
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void leftonclick();

        void rightonclick();
    }
}
