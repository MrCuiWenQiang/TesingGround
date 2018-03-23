package com.wtl.right.widget.builderdialog;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtl.right.R;
import com.wtl.right.util.ResUtil;
import com.wtl.right.util.ViewUtil;

/**
 * Function :菜单类对话框item类
 * Remarks  :
 * Created by Mr.C on 2018/3/22 0022.
 */

public class DialogMenuItemView extends RelativeLayout {

    private int index = -1;

    private MenuItemViewListener listener;
    private boolean mIsChecked = false;

    public DialogMenuItemView(Context context) {
        this(context, null);
    }

    public DialogMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DialogMenuItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        ViewUtil.setBackgroundKeepingPadding(this, ResUtil.getAttrDrawable(context, R.attr.dialog_list_item_bg_with_border_none));
        setPadding(ResUtil.getAttrDimen(context, R.attr.dialog_padding_horizontal), 0,
                ResUtil.getAttrDimen(context, R.attr.dialog_padding_horizontal), 0);
    }

    public TextView createTextView(Context context) {
        TextView tv = new TextView(context);
        tv.setTextColor(ResUtil.getAttrColor(context, R.attr.dialog_menu_item_text_color));
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(context, R.attr.dialog_content_list_item_text_size));
        tv.setSingleLine(true);
        tv.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        tv.setDuplicateParentStateEnabled(false);
        return tv;
    }

    public void setListener(MenuItemViewListener listener) {
        if (!isClickable()) {
            setClickable(true);
        }
        this.listener = listener;
    }

    protected void notifyCheckChange(boolean isChecked) {

    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
        notifyCheckChange(mIsChecked);
    }

    @Override
    public boolean performClick() {
        if (listener != null) {
            listener.onClick(index);
        }
        return super.performClick();
    }

    public int getMenuIndex() {
        return this.index;
    }

    public void setMenuIndex(int index) {
        this.index = index;
    }

    public interface MenuItemViewListener {
        void onClick(int index);
    }

    //单独textView
    public static class TextItemView extends DialogMenuItemView {
        protected TextView mTextView;

        public TextItemView(Context context) {
            super(context);
            init();
        }

        public TextItemView(Context context, CharSequence text) {
            super(context);
            init();
            setText(text);
        }

        private void init() {
            mTextView = createTextView(getContext());
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView(mTextView, layoutParams);
        }

        public void setText(CharSequence text) {
            mTextView.setText(text);
        }

        public void setTextColor(int color) {
            mTextView.setTextColor(color);
        }
    }

    //多选使用item组合
    public static class CheckItemView extends DialogMenuItemView {

        private Context mContext;
        private TextView mTextView;
        private ImageView mCheckedView;

        public CheckItemView(Context context, boolean right) {
            super(context);
            this.mContext = context;
            init(right);
        }

        //可重复使用为左右图标勾选
        private void init(boolean right) {
            mCheckedView = new ImageView(mContext);
            mCheckedView.setImageDrawable(ResUtil.getAttrDrawable(mContext, R.attr.dialog_content_list_item_select));
            mCheckedView.setId(ViewUtil.generateViewId());

            LayoutParams ck_layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ck_layoutParams.addRule(CENTER_VERTICAL, TRUE);
            if (right) {
                ck_layoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
            } else {
                ck_layoutParams.addRule(ALIGN_PARENT_LEFT, TRUE);
            }
            ck_layoutParams.leftMargin = ResUtil.getAttrDimen(mContext, R.attr.dialog_menu_item_check_icon_margin_horizontal);
            addView(mCheckedView, ck_layoutParams);

            mTextView = createTextView(mContext);
            LayoutParams tvLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            if (right) {
                tvLp.addRule(LEFT_OF, mCheckedView.getId());
            } else {
                tvLp.addRule(RIGHT_OF, mCheckedView.getId());
            }
            addView(mTextView, tvLp);
        }

        public CheckItemView(Context context, boolean right, CharSequence text) {
            this(context, right);
            setText(text);
        }

        public CheckItemView(Context context, CharSequence text) {
            this(context, true);
            setText(text);
        }

        public void setText(CharSequence text) {
            mTextView.setText(text);
        }

        @Override
        protected void notifyCheckChange(boolean isChecked) {
            ViewUtil.safeSetImageViewSelected(mCheckedView, isChecked);
        }
    }

    public static class MarkItemView extends DialogMenuItemView {

        private Context mContext;
        private TextView mTextView;
        private ImageView mCheckedView;

        public MarkItemView(Context context) {
            super(context);
            this.mContext = context;
            init();
        }

        public MarkItemView(Context context, CharSequence text) {
            this(context);
            setText(text);
        }

        private void init() {
            mCheckedView = new ImageView(mContext);
            mCheckedView.setImageDrawable(ResUtil.getAttrDrawable(mContext, R.attr.dialog_check_mark));
            mCheckedView.setId(ViewUtil.generateViewId());

            LayoutParams ck_layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ck_layoutParams.addRule(CENTER_VERTICAL, TRUE);
            ck_layoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
            ck_layoutParams.leftMargin = ResUtil.getAttrDimen(mContext, R.attr.dialog_menu_item_check_icon_margin_horizontal);
            addView(mCheckedView, ck_layoutParams);

            mTextView = createTextView(mContext);
            LayoutParams tvLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            tvLp.addRule(LEFT_OF, mCheckedView.getId());
            addView(mTextView, tvLp);
        }


        public void setText(CharSequence text) {
            mTextView.setText(text);
        }

        @Override
        protected void notifyCheckChange(boolean isChecked) {
            ViewUtil.safeSetImageViewSelected(mCheckedView, isChecked);
        }
    }
}
