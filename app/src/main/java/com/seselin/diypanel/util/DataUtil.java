package com.seselin.diypanel.util;


import com.greendao.gen.DaoSession;
import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.bean.HistoryBean;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.bean.PrizeUIBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class DataUtil {

    public static List<PrizeBean> getDevilItems() {

        List<PrizeBean> beans = new ArrayList<>();

        beans.add(new PrizeBean("彩虹怪", 16));
        beans.add(new PrizeBean("帝王天使怪", 16));
        beans.add(new PrizeBean("水晶 15", 224));
        beans.add(new PrizeBean("水晶 100", 20));
        beans.add(new PrizeBean("魔力石 1万", 289));
        beans.add(new PrizeBean("魔力石 5万", 20));
        beans.add(new PrizeBean("魔力石 15万", 15));
        beans.add(new PrizeBean("能量 15", 240));
        beans.add(new PrizeBean("竞技场出场券 5", 120));
        beans.add(new PrizeBean("神秘召唤书", 20));
        beans.add(new PrizeBean("符文", 15));
        beans.add(new PrizeBean("魔灵", 5));

        long id = PrizeBean.generateId();
        for (int i = 0; i < beans.size(); i++) {
            beans.get(i).setId(id + i);
        }

        return beans;
    }

    public static PrizeUIBean getDefaultPrizeBean() {
        return new PrizeUIBean("空白区域", 0);
    }

    public static void initData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        if (0 == mDaoSession.getPrizeBeanDao().loadAll().size()) {
            setDefaultPrizeData();
        }
    }

    public static void clearPrizeData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getPrizeBeanDao().deleteAll();
    }

    public static void setDefaultPrizeData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getPrizeBeanDao().deleteAll();
        for (PrizeBean devilItem : getDevilItems()) {
            mDaoSession.getPrizeBeanDao().insertOrReplace(devilItem);
        }
    }

    public static List<PrizeBean> getPrizeData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        List<PrizeBean> beans = mDaoSession.getPrizeBeanDao().loadAll();
        Collections.sort(beans);
        return beans;
    }

    public static boolean isFirstOpen() {
        String firstOpenKey = "firstOpen";
        boolean isFirstOpen = SPUtils.getBoolean(BaseApplication.getContext()
                , firstOpenKey, true);
        return isFirstOpen;
    }

    public static void clearFirstOpen() {
        String firstOpenKey = "firstOpen";
        SPUtils.putBoolean(BaseApplication.getContext(), firstOpenKey, false);
    }

    public static void addHistoryBean(HistoryBean historyBean) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getHistoryBeanDao().insertOrReplace(historyBean);

    }

}
