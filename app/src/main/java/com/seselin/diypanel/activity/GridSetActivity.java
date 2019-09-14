package com.seselin.diypanel.activity;

import com.seselin.diypanel.base.BaseSelectActivity;
import com.seselin.diypanel.bean.SelectBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.SettingConfig;

import java.util.ArrayList;

/**
 * Created by Seselin on 2019/9/14.
 */
public class GridSetActivity extends BaseSelectActivity {

    @Override
    protected void setTitle() {
        tvTitle.setText("网格数设置");
    }

    @Override
    protected void initData() {
        ArrayList<SelectBean> list = new ArrayList<>();
        list.add(new SelectBean("3*3", "3"));
        list.add(new SelectBean("4*4", "4"));
        list.add(new SelectBean("5*5", "5"));
        selectBeans.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setSelectBean(SelectBean selectBean) {
        SettingConfig.saveGridNum(selectBean);
        EventBusTag.post(EventBusTag.SET_GRID_NUM);
        finish();
    }

}
