package com.tenhrs.courses.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tenhrs.courses.R;

/**
 * Created by SIVA on 22-11-2016.
 */

public class IntroductionPageAdapter extends PagerAdapter {
    private Context mContext;

    public IntroductionPageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = null;
        switch (position) {
            case 0:
                layout = (ViewGroup) inflater.inflate(R.layout.introduction_page1, collection, false);
                break;
            case 1:
                layout = (ViewGroup) inflater.inflate(R.layout.introduction_page2, collection, false);
                break;
            case 2:
                layout = (ViewGroup) inflater.inflate(R.layout.introduction_page3, collection, false);
                break;
        }

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
