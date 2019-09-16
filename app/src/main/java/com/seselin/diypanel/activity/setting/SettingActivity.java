package com.seselin.diypanel.activity.setting;

import android.widget.TextView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.CheckBean;
import com.seselin.diypanel.bean.PlanBean;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.window.CheckWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Seselin on 2019/8/21.
 */
public class SettingActivity extends TitleBarActivity {

    @BindView(R.id.tv_set_num)
    TextView tvSetNum;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleTv("设置");
    }

    @OnClick(R.id.tv_set_num)
    void setNum() {
        goActivity(GridSetActivity.class);
    }

    Long planId = 2L;

    @OnClick(R.id.tv_select_combination)
    void selectCombination() {
        PlanBean myPlan = DataUtil.getPlanById(planId);
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(myPlan.getPrizeList());

        ArrayList<PrizeBean> dataList = (ArrayList<PrizeBean>) DataUtil.getPrizeData();
        ArrayList<CheckBean> checkList = new ArrayList<>();
        for (PrizeBean prizeBean : dataList) {
            String key = String.format("%d", prizeBean.getId());

            CheckBean checkBean = new CheckBean()
                    .setKey(key)
                    .setName(prizeBean.getName())
                    .setCheck(keyList.contains(key));
            checkList.add(checkBean);
        }
        CheckWindow checkWindow = new CheckWindow(mContext, checkList);
        checkWindow.setOnMenuListener(selectKeyList -> {
            PlanBean planBean = new PlanBean(planId);
            planBean.setName("测试方案");
            planBean.SetPrizeList(selectKeyList);
            DataUtil.addPlanBean(planBean);
        });
        checkWindow.show();
    }

    @OnClick(R.id.tv_edit_combination)
    void editCombination() {
        DataUtil.deletePlanBean(1L);
    }

    @OnClick(R.id.tv_edit_contain)
    void setContain() {
        goActivity(PrizeItemListActivity.class);
    }

    @OnClick(R.id.tv_clear_contain)
    void clearContain() {
        DataUtil.clearPrizeData();
        EventBusTag.send(EventBusTag.PRIZE_DATA_CHANGE);
    }

    @OnClick(R.id.tv_set_default_contain)
    void setDefaultContain() {
        DataUtil.setDefaultPrizeData();
        EventBusTag.send(EventBusTag.PRIZE_DATA_CHANGE);
    }

}
