package com.fzc.lowcopyjiantalk.entity;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.entity
 * 文件名：LoadBody
 * 创建者：fzc
 * 创建日期：2018/5/26 21:36
 * 描述
 */

public class LoadBody {

    public LoadBody(String name , String url){
        this.movieName = name;
        this.imgUrl = url;

    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String movieName;

    private String imgUrl;

}
