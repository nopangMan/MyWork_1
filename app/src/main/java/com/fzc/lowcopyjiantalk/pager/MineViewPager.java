package com.fzc.lowcopyjiantalk.pager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.activity.MineCenterActivity;
import com.fzc.lowcopyjiantalk.base.BasePager;
import com.fzc.lowcopyjiantalk.utils.MySPUtil;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.pager
 * 文件名：MineViewPager
 * 创建者：fzc
 * 创建日期：2018/5/9 17:49
 * 描述   我的页面
 */

public class MineViewPager extends BasePager implements View.OnClickListener {

    private CircleImageView iv_mine_user_img;

    private Button btn_user_messagme;

    private RelativeLayout rl_user_favourite_article;

    private RelativeLayout rl_user_collect_article;

    private RelativeLayout rl_user_articles;

    private SwitchCompat sw_night_module;

    private RelativeLayout rl_user_settings;

    public MineViewPager(Context mContext) {
        super(mContext);
    }

    @Override
    public View initView() {
        View view = View.inflate(rootContext, R.layout.fragment_mine, null);
        iv_mine_user_img = view.findViewById(R.id.iv_mine_user_img);
        iv_mine_user_img.setOnClickListener(this);
        btn_user_messagme = view.findViewById(R.id.btn_user_message_edit);
        btn_user_messagme.setOnClickListener(this);
        rl_user_favourite_article = view.findViewById(R.id.rl_user_favourite_article);
        rl_user_favourite_article.setOnClickListener(this);
        rl_user_collect_article = view.findViewById(R.id.rl_user_collect_article);
        rl_user_collect_article.setOnClickListener(this);
        rl_user_articles = view.findViewById(R.id.rl_user_articles);
        rl_user_articles.setOnClickListener(this);
        rl_user_settings = view.findViewById(R.id.rl_user_settings);
        rl_user_settings.setOnClickListener(this);
        sw_night_module = view.findViewById(R.id.sw_night_module);
        return view;
    }

    @Override
    public void initData() {
        iv_mine_user_img.setImageBitmap(MySPUtil.getImageToShare(rootContext, StaticUtil.USER_HEAD_BITMAP_FILE_NAME));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mine_user_img:
                Intent intent1 = new Intent(rootContext, MineCenterActivity.class);
                rootContext.startActivity(intent1);
                break;
            case R.id.btn_user_message_edit:
                Intent intent2 = new Intent(rootContext, MineCenterActivity.class);
                rootContext.startActivity(intent2);
                break;
            case R.id.rl_user_favourite_article:
                break;
            case R.id.rl_user_collect_article:
                break;
            case R.id.rl_user_articles:
                break;
            case R.id.rl_user_settings:
                break;
        }
    }
}
