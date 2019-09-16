package com.seselin.diypanel.bean;

import android.text.TextUtils;

import com.seselin.diypanel.util.InputUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Seselin on 2019/9/16.
 */
@Entity
public class PlanBean {

    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String prizeListStr;

    public PlanBean() {
    }

    public PlanBean(Long id) {
        this.id = id;
    }

    @Generated(hash = 1535783677)
    public PlanBean(Long id, String name, String prizeListStr) {
        this.id = id;
        this.name = name;
        this.prizeListStr = prizeListStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrizeListStr() {
        return prizeListStr;
    }

    public void setPrizeListStr(String prizeListStr) {
        this.prizeListStr = prizeListStr;
    }

    public List<String> getPrizeList() {
        List<String> prizeIds = new ArrayList<>();
        if (!TextUtils.isEmpty(prizeListStr)) {
            prizeIds = Arrays.asList(prizeListStr.split(","));
        }
        return prizeIds;
    }

    public void SetPrizeList(ArrayList<String> prizeIds) {
        prizeListStr = "";
        for (String prizeId : prizeIds) {
            prizeListStr = InputUtil.addString(prizeListStr, prizeId);
        }
    }

}
