package com.fzc.lowcopyjiantalk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.entity.User;
import com.fzc.lowcopyjiantalk.utils.LogUtil;
import com.fzc.lowcopyjiantalk.utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.activity
 * 文件名：ResignActivity
 * 创建者：fzc
 * 创建日期：2018/5/10 17:01
 * 描述
 */

public class ResignActivity extends OptionItemActivity {


    private User mUser;

    private EditText et_user_account;
    private EditText et_user_password;
    private EditText et_user_email;
    private EditText et_user_desc;

    private Button btn_resign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);

        initView();

        //创建一个用户
        mUser = new User();

        btn_resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户注册事件
                if (!TextUtils.isEmpty(et_user_account.getText()) &&
                        !TextUtils.isEmpty(et_user_password.getText()) &&
                        !TextUtils.isEmpty(et_user_email.getText())
                        ) {
                    mUser.setUsername(et_user_account.getText().toString().trim());
                    mUser.setPassword(et_user_password.getText().toString().trim());
                    mUser.setEmail(et_user_email.getText().toString().trim());
                    mUser.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e == null) {
                                ToastUtil.makeShortToast(ResignActivity.this, "注册成功,请前往邮箱验证");
                                finish();
                            } else {
                                LogUtil.i(e.getMessage());
                            }
                        }
                    });

                } else {
                    ToastUtil.makeShortToast(ResignActivity.this, "账户,密码,邮箱不能为空");
                }
            }
        });
    }

    private void initView() {

        et_user_account = (EditText) findViewById(R.id.et_user_account);
        et_user_password = (EditText) findViewById(R.id.et_user_password);
        et_user_email = (EditText) findViewById(R.id.et_user_email);
        et_user_desc = (EditText) findViewById(R.id.et_user_desc);

        btn_resign = (Button) findViewById(R.id.btn_resign);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
