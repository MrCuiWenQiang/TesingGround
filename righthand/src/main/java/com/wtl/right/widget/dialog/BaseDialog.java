package com.wtl.right.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wtl.right.util.ScreenUtil;

/**
 * Function : 基类的对话框
 * Remarks  :
 * Created by Mr.C on 2018/3/12 0012.
 */

public abstract class BaseDialog extends DialogFragment implements BaseDialogInterFace{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
        initData(savedInstanceState);
        initListener();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.height = getDialogHeght();
        attributes.width = getDialogWidth();
        initLayoutParams(attributes);
        getDialog().getWindow().setAttributes(attributes);
    }

    protected int getDialogWidth() {
        return ScreenUtil.getWindowWidth(getContext()) *2/3;
    }

    protected int getDialogHeght() {
        if (ScreenUtil.isCreenOriatationPortrait(getContext())) {
            return ScreenUtil.getWindowHeight(getContext()) / 3;
        } else {
            return ScreenUtil.getWindowHeight(getContext()) * 2 / 3;
        }
    }

    protected void initLayoutParams(WindowManager.LayoutParams attributes){

    }
}
