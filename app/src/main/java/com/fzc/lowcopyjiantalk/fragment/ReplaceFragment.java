package com.fzc.lowcopyjiantalk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fzc.lowcopyjiantalk.base.BasePager;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.fragment
 * 文件名：ReplaceFragment
 * 创建者：fzc
 * 创建日期：2018/5/9 18:31
 * 描述
 */

public class ReplaceFragment extends Fragment {


    private BasePager mBasePager;

    public ReplaceFragment(BasePager basePager){

        mBasePager = basePager;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mBasePager != null){
            return mBasePager.rootView;
        }
        return null;
    }
}
