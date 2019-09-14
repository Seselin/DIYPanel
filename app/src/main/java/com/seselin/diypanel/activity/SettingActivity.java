package com.seselin.diypanel.activity;

import android.widget.TextView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.base.TitleBarActivity;

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

    @OnClick(R.id.tv_set_contain)
    void setContain() {
        ToastShow("设置内容！");
    }

}
