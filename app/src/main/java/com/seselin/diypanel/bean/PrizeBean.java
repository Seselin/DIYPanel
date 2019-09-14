package com.seselin.diypanel.bean;

/**
 * Created by Seselin on 2019/7/2.
 */
public class PrizeBean {

    private boolean isFocus;
    private boolean isShow;
    private int weight;// 奖品权重
    private int imageRes;// 奖品图片
    private String name;// 奖品名称

    public PrizeBean() {
    }

    public PrizeBean(String name, int weight) {
        this(name, weight, 0);
    }

    public PrizeBean(String name, int weight, int imageRes) {
        this.weight = weight;
        this.imageRes = imageRes;
        this.name = name;
        isShow = true;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        isShow = true;
    }

}
