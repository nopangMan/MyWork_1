package com.fzc.lowcopyjiantalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.entity.User;
import com.fzc.lowcopyjiantalk.utils.LogUtil;
import com.fzc.lowcopyjiantalk.utils.MySPUtil;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;
import com.fzc.lowcopyjiantalk.utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.activity
 * 文件名：LoginActivity
 * 创建者：fzc
 * 创建日期：2018/5/9 22:36
 * 描述
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private User myUser;

    private TextView tv_forget_pwd;

    private Button btn_resign;

    private Button btn_login;

    private EditText et_account;

    private EditText et_pwd;

    private CheckBox cb_remember_pwd;

    private String rebAccount;

    private String rebPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rebAccount = MySPUtil.getString(this, "userAccount", null);

        rebPwd = MySPUtil.getString(this, "userPassWord", null);

        initView();
        //从sp中检查CheckBox的状态,并设置
        setCheckBoxState();
        //根据CheckBox的状态设置账号密码
        setCountAndPwd();

        initData();
    }

    private void setCheckBoxState() {
        if (MySPUtil.getBoolean(this, "needSetPwd", false)) {
            cb_remember_pwd.setChecked(true);
        } else {
            cb_remember_pwd.setChecked(false);
        }
        cb_remember_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StaticUtil.rememberPWdState = true;
                } else {
                    StaticUtil.rememberPWdState = false;
                }
            }
        });
    }


    private void setCountAndPwd() {
        //判断是否需要设置账号密码
        if (MySPUtil.getBoolean(this, "needSetPwd", false)) {
            et_account.setText(rebAccount);
            et_account.requestFocus();
            et_account.setSelection(rebAccount.length());
            et_pwd.setText(rebPwd);
        } else {
            ToastUtil.makeShortToast(this, "没有触发事件");
        }
    }

    private void initData() {
        myUser = new User();
    }

    private void initView() {

        tv_forget_pwd = (TextView) findViewById(R.id.tv_forget_pwd);
        tv_forget_pwd.setOnClickListener(this);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_resign = (Button) findViewById(R.id.btn_resign);
        btn_resign.setOnClickListener(this);

        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        cb_remember_pwd = (CheckBox) findViewById(R.id.cb_remember_pwd);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pwd:
                userForget();
                break;
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.btn_resign:
                userResign();
                break;
        }
    }

    private void userForget() {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }

    private void userResign() {
        startActivity(new Intent(this, ResignActivity.class));
    }

    private void userLogin() {

        if (!TextUtils.isEmpty(et_account.getText()) &&
                !TextUtils.isEmpty(et_pwd.getText())
                ) {
            myUser.setUsername(et_account.getText().toString().trim());
            myUser.setPassword(et_pwd.getText().toString().trim());
            myUser.login(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (e == null) {
                        if (user.getEmailVerified()) {
                            ToastUtil.makeShortToast(LoginActivity.this, "登陆成功");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            ToastUtil.makeShortToast(LoginActivity.this, "用户尚未进行邮箱认证");
                        }
                    } else {
                        LogUtil.i(e.toString());
                        ToastUtil.makeLongToast(LoginActivity.this, e.getMessage());
                    }
                }
            });
        } else {
            ToastUtil.makeShortToast(this, "用户名或密码不能为空");
        }

    }

    @Override
    protected void onDestroy() {
        MySPUtil.putString(this, "userAccount", et_account.getText().toString());
        MySPUtil.putString(this, "userPassWord", et_pwd.getText().toString());
        MySPUtil.putBoolean(this, "needSetPwd", StaticUtil.rememberPWdState);
        super.onDestroy();
    }
}
