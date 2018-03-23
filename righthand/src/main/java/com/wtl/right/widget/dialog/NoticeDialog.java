package com.wtl.right.widget.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wtl.right.R;

/**
 * Function : 带有确定按钮和信息展示的Dialog
 * Remarks  :
 * Created by Mr.C on 2018/3/12 0012.
 */

public class NoticeDialog extends BaseDialog {


    private String title = "公告";
    private String content = "内容";
    private String yes = "确定";

    private OnNoticeDialogClick onNoticeDialogClick;

    private TextView tv_title;
    private TextView tv_content;
    private Button bt_yes;

    @Override
    public int getLayoutId() {
        return R.layout.dg_notice;
    }

    @Override
    public void initview(View v) {
        tv_title = v.findViewById(R.id.tv_title);
        tv_content = v.findViewById(R.id.tv_content);
        bt_yes = v.findViewById(R.id.bt_yes);
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoticeDialogClick != null) {
                    dismiss();
                    onNoticeDialogClick.onClick(v);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tv_title.setText(title);
        tv_content.setText(content);
        bt_yes.setText(yes);

    }

    @Override
    public void initListener() {

    }


    public interface OnNoticeDialogClick {
        void onClick(View view);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = yes;
    }


    public OnNoticeDialogClick getOnNoticeDialogClick() {
        return onNoticeDialogClick;
    }

    public void setOnNoticeDialogClick(OnNoticeDialogClick onNoticeDialogClick) {
        this.onNoticeDialogClick = onNoticeDialogClick;
    }
}
