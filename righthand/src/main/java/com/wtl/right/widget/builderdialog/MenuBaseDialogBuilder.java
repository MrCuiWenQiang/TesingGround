package com.wtl.right.widget.builderdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wtl.right.R;
import com.wtl.right.util.ResUtil;
import com.wtl.right.util.ViewUtil;

import java.util.ArrayList;

/**
 * Function :菜单类对话框buiilder基类
 * Remarks  :
 * Created by Mr.C on 2018/3/22 0022.
 */

public abstract class MenuBaseDialogBuilder<T extends DialogBuilder> extends DialogBuilder<T> {

    protected ArrayList<DialogMenuItemView> mMenuItemViews;
    protected LinearLayout mMenuItemContainer;
    protected LinearLayout.LayoutParams mMenuItemLp;

    public MenuBaseDialogBuilder(Context mContext) {
        super(mContext);
        mMenuItemViews = new ArrayList<>();
        mMenuItemLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ResUtil.getAttrDimen(mContext, R.attr.dialog_content_list_item_height)
        );
        mMenuItemLp.gravity = Gravity.CENTER_VERTICAL;
    }

    public void clear() {
        mMenuItemViews.clear();
    }

    public T addItem(DialogMenuItemView itemView, final DialogInterface.OnClickListener listener) {
        itemView.setMenuIndex(mMenuItemViews.size());
        itemView.setListener(new DialogMenuItemView.MenuItemViewListener() {
            @Override
            public void onClick(int index) {
                onItemClick(index);
                if (listener != null) {
                    listener.onClick(dialog.getDialog(), index);
                }
            }
        });
        mMenuItemViews.add(itemView);
        return (T) this;
    }

    protected void onItemClick(int index) {

    }

    @Override
    public void createCentent(MyDialog dialog, ViewGroup layoutGroup) {
        mMenuItemContainer = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mMenuItemContainer.setPadding(
                0, ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_top_when_list),
                0, ResUtil.getAttrDimen(mContext, actionList.size() > 0 ? R.attr.dialog_content_padding_bottom : R.attr.dialog_content_padding_bottom_when_no_action)
        );
        mMenuItemContainer.setLayoutParams(layoutParams);
        mMenuItemContainer.setOrientation(LinearLayout.VERTICAL);
        if (mMenuItemViews.size() == 1) {
            mMenuItemContainer.setPadding(0, 0, 0, 0
            );
            if (hasTitle()) {
                ViewUtil.setPaddingTop(mMenuItemContainer, ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_top_when_list));
            }
            if (actionList.size() > 0) {
                ViewUtil.setPaddingBottom(mMenuItemContainer, ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_bottom));
            }
        }
        for (DialogMenuItemView itemView : mMenuItemViews) {
            mMenuItemContainer.addView(itemView, mMenuItemLp);
        }
        ScrollView scrollView = new ScrollView(mContext) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(getmContentAreaMaxHeight(),
                        MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        };
        scrollView.addView(mMenuItemContainer);
        layoutGroup.addView(scrollView);
    }
}
