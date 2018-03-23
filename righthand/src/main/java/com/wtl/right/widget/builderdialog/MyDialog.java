package com.wtl.right.widget.builderdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtl.right.R;
import com.wtl.right.util.DisplayUtil;
import com.wtl.right.util.ResUtil;
import com.wtl.right.widget.WrapContentScrollView;

import java.util.ArrayList;

/**
 * Function : 使用构建者模式的对话框
 * Remarks  : BUG 多列表选择无法获取选择项
 * Created by Mr.C on 2018/3/19 0019.
 */

public class MyDialog extends DialogFragment {
    public View layoutView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }

    //消息类型对话框
    public static class MessageDialogBuilder extends DialogBuilder<MessageDialogBuilder> {

        private TextView textView;
        private String message;
        private Context mContext;
        private WrapContentScrollView mScrollView;

        public MessageDialogBuilder(Context mContext) {
            super(mContext);
            initView(mContext);
            this.mContext = mContext;
        }

        public MessageDialogBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        private void initView(Context mContext) {
            mScrollView = new WrapContentScrollView(mContext);
            textView = new TextView(mContext);
            textView.setLineSpacing(DisplayUtil.dpToPx(2), 1.0F);
            textView.setTextColor(ResUtil.getAttrColor(mContext, R.attr.dialog_message_color_gray));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_message_textsize));
            mScrollView.addView(textView);
        }

        @Override
        public void createCentent(MyDialog dialog, ViewGroup layoutGroup) {
            if (!TextUtils.isEmpty(message)) {
                mScrollView.setMaxHeight(getmContentAreaMaxHeight());
                textView.setText(message);
                textView.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_left),
                        hasTitle() ? ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_top) : ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_notitle_top),
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_right),
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_bottom));
                layoutGroup.addView(mScrollView);
            }
        }
    }

    //带有选择的消息对话框
    public static class CheckBoxMessageDialogBuilder extends MenuBaseDialogBuilder<CheckBoxMessageDialogBuilder> {

        private Context mContext;
        private String message;
        private Drawable mDrawable;
        private TextView textView;
        private WrapContentScrollView mScrollView;
        private boolean mIsChecked = false;

        public CheckBoxMessageDialogBuilder(Context mContext) {
            super(mContext);
            this.mContext = mContext;
            initCentent(mContext);
        }

        private void initCentent(Context mContext) {
            mDrawable = ResUtil.getAttrDrawable(mContext, R.attr.dialog_content_select);
            mScrollView = new WrapContentScrollView(mContext);
            textView = new TextView(mContext);
            textView.setLineSpacing(DisplayUtil.dpToPx(2), 1.0F);
            textView.setTextColor(ResUtil.getAttrColor(mContext, R.attr.dialog_message_color_gray));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_message_textsize));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            mScrollView.addView(textView);

        }

        public CheckBoxMessageDialogBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public CheckBoxMessageDialogBuilder setMessage(int resid) {
            return setMessage(mContext.getResources().getString(resid));
        }

        public CheckBoxMessageDialogBuilder setChecked(boolean checked) {
            if (checked != mIsChecked) {
                mIsChecked = checked;
                if (textView != null) {
                    textView.setSelected(mIsChecked);
                }
            }
            return this;
        }

        @Override
        public void createCentent(MyDialog dialog, ViewGroup layoutGroup) {
            if (!TextUtils.isEmpty(message)) {
                mScrollView.setMaxHeight(getmContentAreaMaxHeight());
                textView.setText(message);
                textView.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_left),
                        hasTitle() ? ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_top) : ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_notitle_top),
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_right),
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_bottom));
                mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
                textView.setCompoundDrawables(mDrawable, null, null, null);
                textView.setCompoundDrawablePadding(DisplayUtil.dpToPx(12));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChecked(!mIsChecked);
                    }
                });
                setChecked(mIsChecked);
                layoutGroup.addView(mScrollView);
            }
        }


    }

    //列表对话框
    public static class MenuDialogBuilder extends MenuBaseDialogBuilder<MenuDialogBuilder>{

        public MenuDialogBuilder(Context mContext) {
            super(mContext);
        }

        public MenuDialogBuilder addItems(CharSequence[] items, DialogInterface.OnClickListener onClickListener){
            for (CharSequence item : items){
                addItem(new DialogMenuItemView.TextItemView(mContext, item),onClickListener);
            }
            return this;
        }
    }

    //单选对话框
    public static class CheckableDialogBuilder extends  MenuBaseDialogBuilder<MenuDialogBuilder>{

        private int mCheckedIndex = -1;

        public CheckableDialogBuilder(Context mContext) {
            super(mContext);
        }

        /**
         * 获取当前选中的菜单项的下标
         *
         * @return 负数表示没选中任何项
         */
        public int getCheckedIndex() {
            return mCheckedIndex;
        }

        /**
         * 设置选中的菜单项的下班
         */
        public CheckableDialogBuilder setCheckedIndex(int checkedIndex) {
            mCheckedIndex = checkedIndex;
            return this;
        }

        @Override
        public void createCentent(MyDialog dialog, ViewGroup layoutGroup) {
            super.createCentent(dialog, layoutGroup);
            if (mCheckedIndex > -1 && mCheckedIndex < mMenuItemViews.size()) {
                mMenuItemViews.get(mCheckedIndex).setChecked(true);
            }
        }

        @Override
        protected void onItemClick(int index) {
            for (int i = 0; i < mMenuItemViews.size(); i++) {
                DialogMenuItemView itemView = mMenuItemViews.get(i);
                if (i == index) {
                    itemView.setChecked(true);
                    mCheckedIndex = index;
                } else {
                    itemView.setChecked(false);
                }
            }
        }

        /**
         * 添加菜单项
         *
         * @param items    所有菜单项的文字
         * @param listener 菜单项的点击事件,可以在点击事件里调用 {@link #setCheckedIndex(int)} 来设置选中某些菜单项
         */
        public CheckableDialogBuilder addItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            for (CharSequence item : items) {
                addItem(new DialogMenuItemView.MarkItemView(mContext, item), listener);
            }
            return this;
        }
    }

    //多选菜单选择对话框
    public static class MultipleChoiceDialogBuilder extends MenuBaseDialogBuilder<MultipleChoiceDialogBuilder> {


        /**
         * 该 int 的每一位标识菜单的每一项是否被选中 (1为选中,0位不选中)
         */
        private int mCheckedItems;

        public MultipleChoiceDialogBuilder(Context mContext) {
            super(mContext);
        }

        /**
         * 设置被选中的菜单项的下标
         *
         * @param checkedItems <b>注意: 该 int 参数的每一位标识菜单项的每一项是否被选中</b>
         *                     <p>如 20 表示选中下标为 1、3 的菜单项, 因为 (2<<1) + (2<<3) = 20</p>
         */
        public MultipleChoiceDialogBuilder setCheckedItems(int checkedItems) {
            mCheckedItems = checkedItems;
            return this;
        }

        /**
         * 设置被选中的菜单项的下标
         *
         * @param checkedIndexes 被选中的菜单项的下标组成的数组,如 [1,3] 表示选中下标为 1、3 的菜单项
         */
        public MultipleChoiceDialogBuilder setCheckedItems(int[] checkedIndexes) {
            int checkedItemRecord = 0;
            for (int checkedIndexe : checkedIndexes) {
                checkedItemRecord += 2 << (checkedIndexe);
            }
            return setCheckedItems(checkedItemRecord);
        }

        /**
         * 添加菜单项
         *
         * @param items    所有菜单项的文字
         * @param listener 菜单项的点击事件,可以在点击事件里调用 {@link #setCheckedItems(int[])}} 来设置选中某些菜单项
         */
        public MultipleChoiceDialogBuilder addItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            for (CharSequence item : items) {
                addItem(new DialogMenuItemView.CheckItemView(mContext, true, item), listener);
            }
            return this;
        }

        @Override
        public void createCentent(MyDialog dialog, ViewGroup layoutGroup) {
            super.createCentent(dialog, layoutGroup);
            for (int i = 0; i < mMenuItemViews.size(); i++) {
                DialogMenuItemView itemView = mMenuItemViews.get(i);
                int v = 2 << i;
                itemView.setChecked((v & mCheckedItems) == v);
            }
        }


        @Override
        protected void onItemClick(int index) {
            DialogMenuItemView itemView = mMenuItemViews.get(index);
            itemView.setChecked(!itemView.isChecked());
        }

        /**
         * @return 被选中的菜单项的下标 <b>注意: 如果选中的是1，3项(以0开始)，因为 (2<<1) + (2<<3) = 20</b>
         */
        public int getCheckedItemRecord() {
            int output = 0;
            int length = mMenuItemViews.size();

            for (int i = 0; i < length; i++) {
                DialogMenuItemView itemView = mMenuItemViews.get(i);
                if (itemView.isChecked()) {
                    output += 2 << itemView.getMenuIndex();
                }
            }
            mCheckedItems = output;
            return output;
        }

        /**
         * @return 被选中的菜单项的下标数组。如果选中的是1，3项(以0开始)，则返回[1,3]
         */
        public int[] getCheckedItemIndexes() {
            ArrayList<Integer> array = new ArrayList<>();
            int length = mMenuItemViews.size();

            for (int i = 0; i < length; i++) {
               DialogMenuItemView itemView = mMenuItemViews.get(i);
                if (itemView.isChecked()) {
                    array.add(itemView.getMenuIndex());
                }
            }
            int[] output = new int[array.size()];
            for (int i = 0; i < array.size(); i++) {
                output[i] = array.get(i);
            }
            return output;
        }

        protected boolean existCheckedItem() {
            return getCheckedItemRecord() <= 0;
        }
    }
}
