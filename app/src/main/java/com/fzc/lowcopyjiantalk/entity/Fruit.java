package com.fzc.lowcopyjiantalk.entity;

/**
 * 项目名：MD_CardView
 * 包名：com.fzc.md_cardview
 * 文件名：Fruit
 * 创建者：fzc
 * 创建日期：2018/5/19 15:04
 * 描述
 */

public class Fruit {
    public int getFruitID() {
        return fruitID;
    }

    public String getFruitName() {
        return fruitName;
    }


    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public void setFruitID(int fruitID) {
        this.fruitID = fruitID;
    }

    private String fruitName;

    private int fruitID;

    public Fruit(String fruitName, int fruitID) {
        this.fruitName = fruitName;
        this.fruitID = fruitID;
    }

}
