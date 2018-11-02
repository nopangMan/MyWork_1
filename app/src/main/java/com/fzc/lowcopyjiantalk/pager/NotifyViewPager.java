package com.fzc.lowcopyjiantalk.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fzc.lowcopyjiantalk.base.BasePager;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.pager
 * 文件名：NotifyViewPager
 * 创建者：fzc
 * 创建日期：2018/5/9 17:48
 * 描述   通知页面
 */

public class NotifyViewPager extends BasePager {

    private TextView tv_main_content;

    public NotifyViewPager(Context mContext) {
        super(mContext);
    }

    @Override
    public View initView() {
        tv_main_content = new TextView(rootContext);
        tv_main_content.setTextColor(Color.GREEN);
        tv_main_content.setGravity(Gravity.CENTER);
        return tv_main_content;
    }

    @Override
    public void initData() {
        tv_main_content.setText("通知");
    }
}
