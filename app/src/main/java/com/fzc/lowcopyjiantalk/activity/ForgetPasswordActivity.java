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

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.activity
 * 文件名：ForgetPasswordActivity
 * 创建者：fzc
 * 创建日期：2018/5/10 16:59
 * 描述
 */

public class ForgetPasswordActivity extends OptionItemActivity implements View.OnClickListener {

    private User mUser;

    private Button btn_reset_pwd_by_memory;
    private Button btn_reset_pwd_by_email;

    private EditText et_old_pwd;
    private EditText et_new_pwd;

    private EditText et_reset_pwd_by_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_activity);

        initView();

        mUser = BmobUser.getCurrentUser(User.class);
    }

    private void initView() {

        et_old_pwd = (EditText) findViewById(R.id.et_user_old_pwd);
        et_new_pwd = (EditText) findViewById(R.id.et_user_new_pwd);

        et_reset_pwd_by_email = (EditText) findViewById(R.id.et_user_reset_pwd_by_email);

        btn_reset_pwd_by_memory = (Button) findViewById(R.id.btn_reset_pwd_by_memory);
        btn_reset_pwd_by_memory.setOnClickListener(this);
        btn_reset_pwd_by_email = (Button) findViewById(R.id.btn_reset_pwd_by_email);
        btn_reset_pwd_by_email.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset_pwd_by_memory:
                if (!TextUtils.isEmpty(et_old_pwd.getText()) && !TextUtils.isEmpty(et_new_pwd.getText())) {
                    User.updateCurrentUserPassword(et_old_pwd.getText().toString().trim(), et_new_pwd.getText().toString().trim(),
                            new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null){
                                        ToastUtil.makeShortToast(ForgetPasswordActivity.this,"修改成功");
                                        finish();
                                    }else {
                                        LogUtil.i(e.toString());
                                    }
                                }
                            });
                } else {
                    ToastUtil.makeShortToast(this, "新旧密码不能为空");
                }
                break;
            case R.id.btn_reset_pwd_by_email:
                if (!TextUtils.isEmpty(et_reset_pwd_by_email.getText())){
                    User.resetPasswordByEmail(et_reset_pwd_by_email.getText().toString().trim(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                ToastUtil.makeShortToast(ForgetPasswordActivity.this, "修改成功,请前往邮箱验证");
                            }else {
                                LogUtil.i(e.toString());
                            }
                        }
                    });
                }else {
                    ToastUtil.makeShortToast(ForgetPasswordActivity.this,"邮箱不能为空");
                }
                break;
        }
    }
}
