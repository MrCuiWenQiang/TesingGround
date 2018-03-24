package com.wtl.right.widget.dialog;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtl.right.R;
import com.wtl.right.util.ResUtil;
import com.wtl.right.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : MyDialog 通用的builder类
 * Remarks  :
 * Created by Mr.C on 2018/3/19 0019.
 */

public abstract class DialogBuilder<T extends DialogBuilder> {

    protected Context mContext;
    private LayoutInflater mLayoutInflater;

    private String title;

    protected MyDialog dialog;

    protected List<DialogAction> actionList = new ArrayList<>();


    private int mContentAreaMaxHeight;

    public DialogBuilder(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        mContentAreaMaxHeight = (int) (ScreenUtil.getWindowHeight(mContext) * 0.75);
    }


    public int getmContentAreaMaxHeight() {
        return mContentAreaMaxHeight;
    }

    public T setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            this.title = title;
        }
        return (T) this;
    }

    public T setTitle(int resId) {
        return setTitle(mContext.getResources().getString(resId));
    }

    public T addAction(String name, DialogAction.ActionListener actionListener) {
        DialogAction action = new DialogAction(name, actionListener);
        actionList.add(action);
        return (T) this;
    }

    public T addAction(String name, int type, DialogAction.ActionListener actionListener) {
        DialogAction action = new DialogAction(name, type, actionListener);
        actionList.add(action);
        return (T) this;
    }

    public boolean hasTitle() {
        return !TextUtils.isEmpty(title);
    }

    public void show(FragmentManager manager, String tag) {
        MyDialog dialog = create();
        dialog.show(manager, tag);
    }

    public MyDialog create() {
        View viewLayout = mLayoutInflater.inflate(R.layout.dg_layout, null);

        dialog = new MyDialog();
        dialog.layoutView = viewLayout;
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.MyDialog);

        LinearLayout group_top = viewLayout.findViewById(R.id.dg_top_group);
        LinearLayout group_centent = viewLayout.findViewById(R.id.dg_centent_group);
        LinearLayout group_bottom = viewLayout.findViewById(R.id.dg_bottom_group);
        createTop(dialog, group_top);
        createCentent(dialog, group_centent);
        createBottom(dialog, group_bottom);
        return dialog;
    }

    public void createTop(MyDialog dialog, ViewGroup layoutGroup) {
        if (hasTitle()) {
            TextView textView = new TextView(mContext);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setText(title);
            textView.setTextColor(ResUtil.getAttrColor(mContext, R.attr.dialog_title_color_black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_title_textsize));
            textView.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_padding_horizontal),
                    ResUtil.getAttrDimen(mContext, R.attr.dialog_title_margin_top),
                    ResUtil.getAttrDimen(mContext, R.attr.dialog_padding_horizontal),
                    0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);
//            ((LinearLayout) layoutGroup).setGravity(Gravity.CENTER);
            layoutGroup.addView(textView);
        }
    }

    public abstract void createCentent(MyDialog dialog, ViewGroup layoutGroup);

    public void createBottom(MyDialog dialog, ViewGroup layoutGroup) {
        int index = actionList.size();
        if (index > 0) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_padding_horizontal),
                    0,
                    ResUtil.getAttrDimen(mContext, R.attr.dialog_padding_horizontal),
                    ResUtil.getAttrDimen(mContext, R.attr.dialog_action_container_margin_bottom));

            for (int i = 0; i < index; i++) {
                DialogAction action = actionList.get(i);
                Button button = action.generateAction(mContext, dialog, i);
                linearLayout.addView(button);
            }
            ((LinearLayout) layoutGroup).setGravity(Gravity.RIGHT);
            layoutGroup.addView(linearLayout);
        }
    }
}
