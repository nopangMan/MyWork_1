package com.fzc.lowcopyjiantalk.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.entity
 * 文件名：User
 * 创建者：fzc
 * 创建日期：2018/5/10 18:43
 * 描述
 */

public class User extends BmobUser {

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
    private Boolean sex;
    private String age;
    private String nickName;
}
