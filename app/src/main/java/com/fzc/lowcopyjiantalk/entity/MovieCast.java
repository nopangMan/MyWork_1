package com.fzc.lowcopyjiantalk.entity;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.entity
 * 文件名：Cast
 * 创建者：fzc
 * 创建日期：2018/5/28 17:49
 * 描述
 */

public class MovieCast {

    public String getCastName() {
        return name;
    }
    public String getCastImgUrl() {
        return imgUrl;
    }

    private String name;

    private String imgUrl;

    public MovieCast(String castName, String castUrl) {
        this.name = castName;
        this.imgUrl = castUrl;
    }


}
