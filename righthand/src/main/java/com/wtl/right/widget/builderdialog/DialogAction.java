package com.wtl.right.widget.builderdialog;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wtl.right.R;
import com.wtl.right.util.ResUtil;

/**
 * Function : 主要存储按钮状态
 * Remarks  :
 * Created by Mr.C on 2018/3/19 0019.
 */

public class DialogAction {


    private int type;
    private String name;
    private ActionListener actionListener;

    //类型
    public static final int ACTION_TYPE_NORMAL = 0;  //正常模式
    public static final int ACTION_TYPE_BLOCK = 1; //异常模式

    public DialogAction(String name, ActionListener actionListener) {
        this(name,  ACTION_TYPE_NORMAL,actionListener);
    }

    public DialogAction(String name, int type, ActionListener actionListener) {
        this.name = name;
        this.type = type;
        this.actionListener = actionListener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public Button generateAction(Context mContext, MyDialog dialog, int index) {
        Button button = createButton(mContext, dialog, index);
        if (type == ACTION_TYPE_NORMAL) {
            button.setTextColor(ResUtil.getAttrColorStateList(mContext,R.attr.dialog_action_text_color));
        } else {
            button.setTextColor(ResUtil.getAttrColorStateList(mContext,R.attr.dialog_action_text_negative_color));
        }
        return button;
    }

    private Button createButton(Context mContext, final MyDialog dialog, final int index) {
        final Button button = new Button(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(layoutParams);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_action_button_text_size));
        button.setText(name);
        button.setClickable(true);
        button.setPadding(0, 0, 0, 0);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        button.setLayoutParams(lp);
        button.setBackground(mContext.getResources().getDrawable(R.drawable.dg_action_button_backage));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.isClickable()) {
                    if (actionListener!=null){
                        actionListener.onClick(dialog, index);
                    }
                }
            }
        });
        return button;
    }

    public interface ActionListener {
        void onClick(MyDialog dialog, int index);
    }
}
