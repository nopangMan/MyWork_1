package com.fzc.lowcopyjiantalk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.adapter
 * 文件名：MovieFragmentAdapter
 * 创建者：fzc
 * 创建日期：2018/5/23 14:47
 * 描述
 */

public class MovieFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    private String[] movieTitles = {"简介", "导演", "风格", "演员"};

    public MovieFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mFragments = list;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return movieTitles[position];
    }
}
