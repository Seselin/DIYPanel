package com.seselin.diypanel.bean;

/**
 * Created by Seselin on 2018/5/31 14:03.
 */
public class CheckBean {

    private boolean isCheck;//是否被标记
    private String name;//用于选项显示
    private String key;//选项的关键字
    private Object object;

    public CheckBean() {
    }

    public CheckBean(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public CheckBean setCheck(boolean check) {
        isCheck = check;
        return this;
    }

    public String getName() {
        return name;
    }

    public CheckBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getKey() {
        return key;
    }

    public CheckBean setKey(String key) {
        this.key = key;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public CheckBean setObject(Object object) {
        this.object = object;
        return this;
    }

}