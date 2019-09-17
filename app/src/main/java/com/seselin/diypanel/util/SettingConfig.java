package com.seselin.diypanel.util;

import android.text.TextUtils;

import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.bean.GridBean;
import com.seselin.diypanel.bean.PlanBean;

import java.util.List;

/**
 * Created by Seselin on 2019/9/14.
 */
public class SettingConfig {

    public static final String FIRST_OPEN = "first_open";
    public static final String GRID_NUM = "grid_num";
    public static final String PLAN_ID = "plan_id";

    public static void saveGridNum(GridBean bean) {
        HistoryUtil.saveObject(BaseApplication.getContext(), GRID_NUM, bean);
    }

    public static GridBean getGridNum() {
        GridBean bean = HistoryUtil.loadObject(BaseApplication.getContext(), GRID_NUM, GridBean.class);
        if (bean == null) {
            bean = new GridBean("4*4", "4");
        }
        return bean;
    }

    public static boolean isFirstOpen() {
        boolean isFirstOpen = SPUtils.getBoolean(BaseApplication.getContext()
                , FIRST_OPEN, true);
        return isFirstOpen;
    }

    public static void clearFirstOpen() {
        SPUtils.putBoolean(BaseApplication.getContext(), FIRST_OPEN, false);
    }

    public static PlanBean getPlan() {
        PlanBean planBean = null;
        String planId = SPUtils.getString(BaseApplication.getContext(), PLAN_ID, "");
        if (!TextUtils.isEmpty(planId)) {
            planBean = DataUtil.getPlanById(Long.valueOf(planId));
            if (planBean != null) {
                return planBean;
            }
        }
        List<PlanBean> planBeanList = DataUtil.getPlanList();
        if (planBean == null && planBeanList.size() > 0) {
            planBean = planBeanList.get(0);
        }
        return planBean;
    }

    public static void setPlanId(String planId) {
        SPUtils.putString(BaseApplication.getContext(), PLAN_ID, planId);
    }

}
