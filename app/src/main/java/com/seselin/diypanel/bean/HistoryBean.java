package com.seselin.diypanel.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Seselin on 2019/8/19.
 */
@Entity
public class HistoryBean {

    long time;
    String prizeName;

    @Generated(hash = 689470442)
    public HistoryBean(long time, String prizeName) {
        this.time = time;
        this.prizeName = prizeName;
    }

    @Generated(hash = 48590348)
    public HistoryBean() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

}
