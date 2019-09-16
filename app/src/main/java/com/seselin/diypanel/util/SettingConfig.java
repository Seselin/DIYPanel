package com.seselin.diypanel.util;

import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.bean.SelectBean;

/**
 * Created by Seselin on 2019/9/14.
 */
public class SettingConfig {

    public static final String GRID_NUM = "grid_num";

    public static void saveGridNum(SelectBean bean) {
        HistoryUtil.saveObject(BaseApplication.getContext(), GRID_NUM, bean);
    }

    public static SelectBean getGridNum() {
        SelectBean bean = HistoryUtil.loadObject(BaseApplication.getContext(), GRID_NUM, SelectBean.class);
        if (bean == null) {
            bean = new SelectBean("4*4", "4");
        }
        return bean;
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

}
