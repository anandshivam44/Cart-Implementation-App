package com.project.cartimplementation.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.project.cartimplementation.ItemFragment;
import com.project.cartimplementation.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {

//    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_0,R.string.tab_text_1, R.string.tab_text_2};
    private static  String[] TAB_TITLES = {"Main Course","Starter","Meal"};

    private final Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

            return ItemFragment.newInstance(position,TAB_TITLES[position]);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 1;
    }
}