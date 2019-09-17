package com.seselin.diypanel.activity.setting;

import android.os.Message;
import android.widget.TextView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.CheckBean;
import com.seselin.diypanel.bean.GridBean;
import com.seselin.diypanel.bean.PlanBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.util.SettingConfig;
import com.seselin.diypanel.window.SelectWindow;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Seselin on 2019/8/21.
 */
public class SettingActivity extends TitleBarActivity {

    {
        useEventBus = true;
    }

    @BindView(R.id.tv_num_config)
    TextView tvNumConfig;
    @BindView(R.id.tv_plan_config)
    TextView tvPlanConfig;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleTv("设置");

        initGridNum();
        initPlanSet();
    }

    private void initPlanSet() {
        PlanBean planBean = SettingConfig.getPlan();
        if (planBean != null) {
            tvPlanConfig.setText(planBean.getName());
        }
    }

    private void initGridNum() {
        GridBean gridBean = SettingConfig.getGridNum();
        tvNumConfig.setText(gridBean.getName());
    }

    @OnClick(R.id.ll_set_num)
    void setNum() {
        ArrayList<GridBean> beans = DataUtil.getGridBeans();
        ArrayList<CheckBean> checkList = new ArrayList<>();
        for (GridBean bean : beans) {
            CheckBean checkBean = new CheckBean(bean.getName(), bean.getValue());
            checkList.add(checkBean);
        }
        SelectWindow selectWindow = new SelectWindow(mContext, checkList);
        selectWindow.setOnMenuListener(selectKey -> {
            GridBean gridBean = DataUtil.getGridBean(selectKey);
            SettingConfig.saveGridNum(gridBean);
            EventBusTag.send(EventBusTag.SET_GRID_NUM);
        });
        selectWindow.show();
    }

    @OnClick(R.id.ll_select_plan)
    void selectCombination() {
        ArrayList<PlanBean> beans = (ArrayList<PlanBean>) DataUtil.getPlanList();
        ArrayList<CheckBean> checkList = new ArrayList<>();
        for (PlanBean planBean : beans) {
            String key = String.format("%d", planBean.getId());
            CheckBean checkBean = new CheckBean()
                    .setKey(key)
                    .setName(planBean.getName());
            checkList.add(checkBean);
        }
        SelectWindow selectWindow = new SelectWindow(mContext, checkList);
        selectWindow.setOnMenuListener(selectKey -> {
            SettingConfig.setPlanId(selectKey);
            EventBusTag.send(EventBusTag.PRIZE_PLAN_CHANGE);
        });
        selectWindow.show();
    }

    @OnClick(R.id.tv_edit_plan)
    void editPlanList() {
        goActivity(PlanListActivity.class);
    }

    @OnClick(R.id.tv_edit_contain)
    void setContain() {
        goActivity(PrizeItemListActivity.class);
    }

    @OnClick(R.id.tv_set_default_contain)
    void setDefaultContain() {
        DataUtil.setDefaultData();
        EventBusTag.send(EventBusTag.PRIZE_DATA_CHANGE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Message message) {
        switch (message.what) {
            case EventBusTag.SET_GRID_NUM:
                initGridNum();
                break;
            case EventBusTag.PRIZE_PLAN_CHANGE:
                initPlanSet();
                break;
            default:
                break;
        }
    }

}
