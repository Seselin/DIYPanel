package com.seselin.diypanel.activity.setting;

import android.widget.TextView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;

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

    @OnClick(R.id.tv_select_combination)
    void selectCombination() {

    }

    @OnClick(R.id.tv_edit_combination)
    void editCombination() {

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
