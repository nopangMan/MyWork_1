package com.fzc.lowcopyjiantalk.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.entity.User;
import com.fzc.lowcopyjiantalk.utils.MySPUtil;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;
import com.fzc.lowcopyjiantalk.view.CustomDialog;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.activity
 * 文件名：MineCenterActivity
 * 创建者：fzc
 * 创建日期：2018/5/15 7:10
 * 描述   个人中心
 */

public class MineCenterActivity extends OptionItemActivity implements View.OnClickListener {
    //背景图片设置位置 0 为 background  1 为 head
    private static int position = 0;

    private User mUser;

    private CustomDialog mCustomDialog;

    private File tempFile = null;
    private File bgFile = null;

    private ImageView iv_mine_msg_img;
    private CircleImageView iv_mine_head_img;

    private EditText et_mine_nick_name;
    private EditText et_mine_age;
    private EditText et_mine_desc;

    private RadioGroup rg_sex;
    private RadioButton rb_male, rb_female;

    private Button btn_goto_camera;
    private Button btn_goto_photo;
    private Button btn_cancel;

    private Button btn_mine_msg_submit;
    private Button btn_mine_msg_exit;

    private String name;
    private String age;
    private boolean sex;
    private String desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message);
        initView();

        setData();
    }

    private void setData() {

        iv_mine_head_img.setImageBitmap(MySPUtil.getImageToShare(this,StaticUtil.USER_HEAD_BITMAP_FILE_NAME));
        iv_mine_msg_img.setImageBitmap(MySPUtil.getImageToShare(this,StaticUtil.USER_BACKGROUND_BITMAP_FILE_NAME));

        et_mine_nick_name.setText(MySPUtil.getString(this, "userNickName", null));
        et_mine_nick_name.requestFocus();
        et_mine_age.setText(MySPUtil.getString(this, "userAge", null));
        et_mine_desc.setText(MySPUtil.getString(this, "userDesc", null));

        if (MySPUtil.getBoolean(this, "userSex", false)) {
            rb_male.setChecked(true);
        } else {
            rb_female.setChecked(true);
        }
    }

    private void handleData() {

        //判断性别 男为true 女为false
        if (rb_male.isChecked()) {
            sex = true;
        } else {
            sex = false;
        }

        name = et_mine_nick_name.getText().toString().trim();
        age = et_mine_age.getText().toString().trim();
        desc = et_mine_desc.getText().toString().trim();

        mUser = new User();
        mUser.setNickName(name);
        mUser.setAge(age);
        mUser.setDesc(desc);
        mUser.setSex(sex);

        MySPUtil.putString(this, "userNickName", name);
        MySPUtil.putString(this, "userAge", age);
        MySPUtil.putString(this, "userDesc", desc);
        MySPUtil.putBoolean(this, "userSex", sex);
    }

    private void initView() {

        mCustomDialog = new CustomDialog(this, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, R.layout.bottom_dialog, R.style.bottom_dialog_style, Gravity.BOTTOM, 0);
        mCustomDialog.setCanceledOnTouchOutside(true);

        iv_mine_msg_img = (ImageView) findViewById(R.id.iv_mine_msg_img);
        iv_mine_msg_img.setEnabled(true);
        iv_mine_msg_img.setOnClickListener(this);
        iv_mine_head_img = (CircleImageView) findViewById(R.id.iv_mine_head_img);
        iv_mine_head_img.setEnabled(true);
        iv_mine_head_img.setOnClickListener(this);

        et_mine_nick_name = (EditText) findViewById(R.id.et_mine_nick_name);
        et_mine_nick_name.setEnabled(true);
        et_mine_age = (EditText) findViewById(R.id.et_mine_age);
        et_mine_age.setEnabled(true);
        et_mine_desc = (EditText) findViewById(R.id.et_mine_desc);
        et_mine_desc.setEnabled(true);

        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);


        btn_mine_msg_submit = (Button) findViewById(R.id.btn_mine_msg_submit);
        btn_mine_msg_submit.setOnClickListener(this);
        btn_mine_msg_exit = (Button) findViewById(R.id.btn_mine_msg_exit);
        btn_mine_msg_exit.setOnClickListener(this);

        btn_goto_camera = mCustomDialog.findViewById(R.id.btn_goto_camera);
        btn_goto_camera.setOnClickListener(this);
        btn_goto_photo = mCustomDialog.findViewById(R.id.btn_goto_photo);
        btn_goto_photo.setOnClickListener(this);
        btn_cancel = mCustomDialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mine_msg_img:
                mCustomDialog.show();
                position = 0;
                break;
            case R.id.iv_mine_head_img:
                mCustomDialog.show();
                position = 1;
                break;
            case R.id.btn_mine_msg_submit:
                handleData();
                iv_mine_msg_img.setEnabled(false);
                iv_mine_head_img.setEnabled(false);
                et_mine_nick_name.setEnabled(false);
                et_mine_age.setEnabled(false);
                et_mine_desc.setEnabled(false);
                break;
            case R.id.btn_goto_camera:
                gotoCamera();
                mCustomDialog.dismiss();
                break;
            case R.id.btn_goto_photo:
                gotoPhoto();
                mCustomDialog.dismiss();
                break;
            case R.id.btn_cancel:
                mCustomDialog.dismiss();

                break;
            case R.id.btn_mine_msg_exit:
                finish();
                break;
        }
    }

    private void gotoPhoto() {
        if (position == 1) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, StaticUtil.PHOTO_HEAD_RESULT_CODE);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, StaticUtil.PHOTO_BACKGROUND_RESULT_CODE);
        }

    }

    private void gotoCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //判断内存卡是否可用
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        StaticUtil.USER_HEAD_IMG_FILE_NAME)));
        startActivityForResult(intent, StaticUtil.CAMERA_RESULT_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != this.RESULT_CANCELED) {
            switch (requestCode) {
                case StaticUtil.CAMERA_RESULT_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), StaticUtil.USER_HEAD_IMG_FILE_NAME);
                    cropPicture(Uri.fromFile(tempFile));
                    break;
                case StaticUtil.PHOTO_HEAD_RESULT_CODE:
                    cropPicture(data.getData());
                    break;
                case StaticUtil.PHOTO_BACKGROUND_RESULT_CODE:
                    cropPicture(data.getData());
                    break;
                case StaticUtil.CROP_RESULT_CODE:
                    if (position == 1) {
                        setCircleImg(data);
                    } else {
                        setRectImg(data);
                    }

                    if (tempFile != null) {
                        tempFile.delete();
                    }
                    break;
            }
        }
    }

    private void setUserBackgroundImg(Uri uri) {
        try {
            bgFile = new File(new URI(uri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String picturePath = bgFile.getAbsolutePath();

        iv_mine_msg_img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }

    private void cropPicture(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (position ==1){
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 320);
            intent.putExtra("outputY", 320);
        }else {
            intent.putExtra("crop", "false");
        }

        //裁剪比例


        intent.putExtra("return-data", true);
        startActivityForResult(intent, StaticUtil.CROP_RESULT_CODE);
    }

    private void setCircleImg(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = bundle.getParcelable("data");
        iv_mine_head_img.setImageBitmap(bitmap);
    }

    private void setRectImg(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = bundle.getParcelable("data");
        iv_mine_msg_img.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySPUtil.putImageToShare(this,StaticUtil.USER_HEAD_BITMAP_FILE_NAME, iv_mine_head_img);
        MySPUtil.putImageToShare(this,StaticUtil.USER_BACKGROUND_BITMAP_FILE_NAME, iv_mine_msg_img);
    }
}
