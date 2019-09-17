package com.seselin.diypanel.util;


import android.text.TextUtils;

import com.greendao.gen.DaoSession;
import com.greendao.gen.HistoryBeanDao;
import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.bean.GridBean;
import com.seselin.diypanel.bean.HistoryBean;
import com.seselin.diypanel.bean.PlanBean;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.bean.PrizeUIBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class DataUtil {

    public static ArrayList<GridBean> getGridBeans() {
        ArrayList<GridBean> beans = new ArrayList<>();
        beans.add(new GridBean("3*3", "3"));
        beans.add(new GridBean("4*4", "4"));
        beans.add(new GridBean("5*5", "5"));
        return beans;
    }

    public static GridBean getGridBean(String key) {
        ArrayList<GridBean> beans = getGridBeans();
        for (GridBean bean : beans) {
            if (key.equals(bean.getValue())) {
                return bean;
            }
        }
        return new GridBean("4*4", "4");
    }

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

    public static void addPrizeBean(PrizeBean prizeBean) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getPrizeBeanDao().insertOrReplace(prizeBean);
    }

    public static void deletePrizeBean(Long id) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getPrizeBeanDao().deleteByKey(id);
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

    public static List<PrizeBean> getPrizeAllData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        List<PrizeBean> beans = mDaoSession.getPrizeBeanDao().loadAll();
        Collections.sort(beans);
        return beans;
    }

    public static List<PrizeBean> getPrizeDataByKey(ArrayList<String> keyList) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        List<PrizeBean> beans = new ArrayList<>();
        for (String key : keyList) {
            PrizeBean bean = mDaoSession.getPrizeBeanDao().load(Long.valueOf(key));
            if (bean != null && !TextUtils.isEmpty(bean.getName())) {
                beans.add(bean);
            }
        }
        Collections.sort(beans);
        return beans;
    }

    public static void addHistoryBean(HistoryBean historyBean) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getHistoryBeanDao().insertOrReplace(historyBean);
    }

    public static List<HistoryBean> getHistoryDataByPage(int page, int offset) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        QueryBuilder queryBuilder = mDaoSession.getHistoryBeanDao().queryBuilder()
                .orderDesc(HistoryBeanDao.Properties.Time)
                .offset(page * offset)
                .limit(offset);
        List<HistoryBean> beans = queryBuilder.list();
        return beans;
    }

    public static void clearHistoryData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getHistoryBeanDao().deleteAll();
    }

    public static void addPlanBean(PlanBean planBean) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getPlanBeanDao().insertOrReplace(planBean);
    }

    public static void deletePlanBean(Long id) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        mDaoSession.getPlanBeanDao().deleteByKey(id);
    }

    public static PlanBean getPlanById(Long id) {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        return mDaoSession.getPlanBeanDao().load(id);
    }

    public static List<PlanBean> getPlanList() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        return mDaoSession.getPlanBeanDao().loadAll();
    }

}
