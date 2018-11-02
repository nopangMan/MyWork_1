package com.fzc.lowcopyjiantalk.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.base.BasePager;
import com.fzc.lowcopyjiantalk.fragment.ReplaceFragment;
import com.fzc.lowcopyjiantalk.pager.CreateViewPager;
import com.fzc.lowcopyjiantalk.pager.FindViewPager;
import com.fzc.lowcopyjiantalk.pager.FirstViewPager;
import com.fzc.lowcopyjiantalk.pager.MineViewPager;
import com.fzc.lowcopyjiantalk.pager.NotifyViewPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {


    private RelativeLayout layout_main;
    private RadioGroup rg_bottom_tags;
    private RadioButton rb_first;
    private RadioButton rb_find;
    private RadioButton rb_create;
    private RadioButton rb_notify;
    private RadioButton rb_mine;

    //定义Pager的标识
    private int position;
    //存放Pager的集合
    private ArrayList<BasePager> mPagers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        mPagers.add(new FirstViewPager(this));
        mPagers.add(new FindViewPager(this));
        mPagers.add(new CreateViewPager(this));
        mPagers.add(new NotifyViewPager(this));
        mPagers.add(new MineViewPager(this));

        rg_bottom_tags.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        rg_bottom_tags.check(R.id.rb_first);
    }

    //初始化View
    private void initView() {

        layout_main = findViewById(R.id.layout_main);

        rg_bottom_tags = findViewById(R.id.rg_bottom_tags);

        rb_first = findViewById(R.id.rb_first);
        rb_find = findViewById(R.id.rb_find);
        rb_create = findViewById(R.id.rb_create);
        rb_notify = findViewById(R.id.rb_notify);
        rb_mine = findViewById(R.id.rb_mine);
    }

    /**
     * 自定义ViewGroup的监听器
     */
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_first:
                    position = 0;
                    break;
                case R.id.rb_find:
                    position = 1;
                    break;
                case R.id.rb_create:
                    position = 2;
                    break;
                case R.id.rb_notify:
                    position = 3;
                    break;
                case R.id.rb_mine:
                    position = 4;
                    break;
            }
            //为ViewPager监听设置完标识后 启动Fragment
            setFragment();
        }

    }

    private void setFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, new ReplaceFragment(getPager()));
        ft.commit();
    }

    private BasePager getPager() {
        BasePager pager = mPagers.get(position);
        if (pager != null && !pager.isInitData) {
            pager.initData();
            pager.isInitData = true;
        }
        return pager;
    }
}
