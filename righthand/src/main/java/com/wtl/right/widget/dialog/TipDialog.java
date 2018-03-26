package com.wtl.right.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtl.right.R;
import com.wtl.right.util.DisplayUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Function : 浮层提示Dialog  这个dialog并不会自己消息  需要手动调用dimss
 * Remarks  :
 * Created by Mr.C on 2018/3/24 0024.
 */

public class TipDialog extends DialogFragment {

    public View layoutView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }


    public static class Builder {
        /**
         * 不显示任何icon
         */
        public static final int ICON_TYPE_NOTICON = 0;

        /**
         * 显示成功图标
         */
        public static final int ICON_TYPE_SUCCESS = 2;
        /**
         * 显示失败图标
         */
        public static final int ICON_TYPE_FAIL = 3;
        /**
         * 显示信息图标
         */
        public static final int ICON_TYPE_INFO = 4;

        private @IconType
        int mCurrentIconType = ICON_TYPE_NOTICON;

        @IntDef({ICON_TYPE_NOTICON, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO})
        @Retention(RetentionPolicy.SOURCE)
        public @interface IconType {

        }

        private Context mContext;

        private CharSequence mTipWord;

        private LayoutInflater layoutInflater;

        public Builder(Context context) {
            mContext = context;
            layoutInflater = LayoutInflater.from(context);
        }

        //设置类型
        public Builder setType(@IconType int type) {
            mCurrentIconType = type;
            return this;
        }

        //设置显示文本
        public Builder setTipWord(CharSequence tipWord) {
            mTipWord = tipWord;
            return this;
        }

        public TipDialog create() {
            TipDialog dialog = createDialog(true);
            return dialog;
        }

        private TipDialog createDialog(boolean cancekable) {
            View view = layoutInflater.inflate(R.layout.dg_tiplayout, null);
            TipDialog dialog = new TipDialog();
            dialog.setCancelable(cancekable);
            dialog.layoutView = view;
            LinearLayout tiplayout = view.findViewById(R.id.cotentlayout);
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TipDialog);
            if (mCurrentIconType != ICON_TYPE_NOTICON) {
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(layoutParams);
                switch (mCurrentIconType) {
                    case ICON_TYPE_SUCCESS: {
                        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_notify_done));
                        break;
                    }
                    case ICON_TYPE_FAIL: {
                        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_notify_error));
                        break;
                    }
                    case ICON_TYPE_INFO: {
                        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_notify_info));
                        break;
                    }
                }
                tiplayout.addView(imageView);
            }

            if (!TextUtils.isEmpty(mTipWord)) {
                TextView tv_content = new TextView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (mCurrentIconType == ICON_TYPE_NOTICON) {
                    layoutParams.topMargin = DisplayUtil.dpToPx(12);
                } else {
                    layoutParams.topMargin = DisplayUtil.dpToPx(8);
                }
                tv_content.setLayoutParams(layoutParams);
                tv_content.setText(mTipWord);
                tv_content.setGravity(Gravity.CENTER);
                tv_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_content.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                tv_content.setMaxLines(2);
                tv_content.setEllipsize(TextUtils.TruncateAt.END);
                tiplayout.addView(tv_content);
            }
            return dialog;
        }

    }
}
