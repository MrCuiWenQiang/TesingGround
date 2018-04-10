package com.wtl.right.widget;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/3/28 0028.
 */

public abstract class BaseViewPager extends PagerAdapter {

    private SparseArray<Object> mScrapItems = new SparseArray<>();

    protected abstract Object hydrate(ViewGroup container, int position);

    protected abstract void populate(ViewGroup container, Object item, int position);

    protected abstract void destroy(ViewGroup container, int position, Object object);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object item = mScrapItems.get(position);
        if (item == null) {
            item = hydrate(container, position);
        } else {
            mScrapItems.remove(position);
        }
        populate(container, item, position);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        destroy(container, position, object);
        mScrapItems.put(position, object);
    }
}
