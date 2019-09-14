package com.seselin.diypanel.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * Created by Seselin on 2019/7/2.
 */
@Entity
public class PrizeBean implements Comparable<PrizeBean> {

    public static long generateId() {
        return new Date().getTime();
    }

    @Id
    private long Id;

    private String name;// 奖品名称
    private int weight;// 奖品权重
    private int imageRes;// 奖品图片

    public PrizeBean() {
        Id = generateId();
    }

    public PrizeBean(String name, int weight) {
        this(generateId(), name, weight, 0);
    }

    public PrizeBean(long Id, String name, int weight) {
        this(Id, name, weight, 0);
    }

    @Generated(hash = 376612872)
    public PrizeBean(long Id, String name, int weight, int imageRes) {
        this.Id = Id;
        this.name = name;
        this.weight = weight;
        this.imageRes = imageRes;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
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
    }

    @Override
    public int compareTo(PrizeBean prizeBean) {
        if (prizeBean.Id > this.Id) {
            return 1;
        } else if (prizeBean.Id < this.Id) {
            return -1;
        }
        return 0;
    }
}
