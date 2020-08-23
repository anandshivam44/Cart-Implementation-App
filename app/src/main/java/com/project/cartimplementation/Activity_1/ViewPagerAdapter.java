package com.project.cartimplementation.Activity_1;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    //    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_0,R.string.tab_text_1, R.string.tab_text_2};
    private static String[] TAB_TITLES = {"Starter", "Meal","Main"};

    private final Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("MyTag", "position = "+position+" title = "+TAB_TITLES[position]);

        return ViewPagerFragment.newInstance(position, TAB_TITLES);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }
}