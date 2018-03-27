package com.wtl.right.widget;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/3/27 0027.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private Context mContext;


    private GestureDetector gestureDetector;

    private RecyclerView touchView;

    private View childView;

    private DialogFragment dialogFragment;

    public RecyclerItemClickListener(Context mContext, final OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                onItemClickListener.onItemClick(childView, touchView.getChildLayoutPosition(childView));
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                onItemClickListener.onLongClick(childView, touchView.getChildLayoutPosition(childView));
                super.onLongPress(e);
            }
        });
    }

    //持有dialog对象做dimess处理
    public void setDialog(DialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        childView = rv.findChildViewUnder(e.getX(), e.getY());
        touchView = rv;
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onLongClick(View view, int posotion);
    }

}
