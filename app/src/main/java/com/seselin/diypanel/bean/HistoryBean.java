package com.seselin.diypanel.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Seselin on 2019/8/19.
 */
@Entity
public class HistoryBean implements Comparable<HistoryBean> {

    @Id
    long time;

    @Convert(converter = PrizeBeanConverter.class, columnType = String.class)
    PrizeBean prizeBean;

    public HistoryBean() {
    }

    @Generated(hash = 740520565)
    public HistoryBean(long time, PrizeBean prizeBean) {
        this.time = time;
        this.prizeBean = prizeBean;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public PrizeBean getPrizeBean() {
        return prizeBean;
    }

    public void setPrizeBean(PrizeBean prizeBean) {
        this.prizeBean = prizeBean;
    }

    @Override
    public int compareTo(HistoryBean bean) {
        if (bean.time > this.time) {
            return 1;
        } else if (bean.time < this.time) {
            return -1;
        }
        return 0;
    }

    public static class PrizeBeanConverter implements PropertyConverter<PrizeBean, String> {
        @Override
        public PrizeBean convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, PrizeBean.class);
        }

        @Override
        public String convertToDatabaseValue(PrizeBean entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

}
