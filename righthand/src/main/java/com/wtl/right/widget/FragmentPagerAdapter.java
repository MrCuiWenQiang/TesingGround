package com.wtl.right.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/3/28 0028.
 */

public class FragmentPagerAdapter extends BaseViewPager {

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;

    private Fragment mCurrentPrimaryItem = null;

    private ArrayList<Object> data;


    private ArrayList<CharSequence> titles;

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected Object hydrate(ViewGroup container, int position) {
        return data.get(position);
    }

    @Override
    protected void populate(ViewGroup container, Object item, int position) {
        if (fragmentManager == null) {
            return;
        }
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
        }
        String name = makeFragmentTAG(container.getId(), position);
        Fragment fragment = fragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            fragmentTransaction.attach(fragment);
        } else {
            fragment = (Fragment) item;
            fragmentTransaction.add(container.getId(),fragment, name);
        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
    }

    @Override
    public void startUpdate(ViewGroup container) {
        if (container.getId() == View.NO_ID) {
            throw new IllegalStateException("ViewPager with adapter " + this
                    + " requires a view id");
        }
    }


    @Override
    protected void destroy(ViewGroup container, int position, Object object) {
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
        }
        fragmentTransaction.detach((Fragment) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (fragmentTransaction != null) {
            fragmentTransaction.commitNowAllowingStateLoss();
            fragmentTransaction = null;
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((Fragment) object).getView();
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public CharSequence getTitles(int position) {
        return titles == null || position > titles.size() ? "" : titles.get(position);
    }

    public void setTitles(ArrayList<CharSequence> titles) {
        this.titles = titles;
    }

    //生成唯一的TAG
    private String makeFragmentTAG(int viewId, long position) {
        return "TheFragment" + viewId + position;
    }


    public static class Builder {

        private FragmentPagerAdapter adapter;

        public Builder(FragmentManager fragmentManager) {
            adapter = new FragmentPagerAdapter(fragmentManager);
        }


        public Builder addDatas(ArrayList<Object> data) {
            adapter.setData(data);
            return this;
        }

        public Builder addTitles(ArrayList<CharSequence> data) {
            adapter.setTitles(data);
            return this;
        }

        public FragmentPagerAdapter create() {
            return adapter;
        }

        public FragmentPagerAdapter create(ViewPager viewPager) {
            viewPager.setAdapter(adapter);
            return adapter;
        }
    }
}
