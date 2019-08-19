package com.seselin.diypanel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.seselin.diypanel.R;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.view.PanelView;


/**
 * Created by Seselin on 2019/7/2.
 */
public class MainActivity extends TitleBarActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        setTitleTv("主页");
        setLeftTv("历史记录");
        setRightTv("设置");

        PanelView panelView = findViewById(R.id.panel_view);
        tvResult = findViewById(R.id.tv_result);

        panelView.initPanelData(4, DataUtil.getDevilItems());
        panelView.setPanelListener(new PanelView.PanelListener() {

            @Override
            public void onStart(PrizeBean selectBean) {
                tvResult.setText(selectBean.getName());
            }

            @Override
            public void onStop(PrizeBean selectBean) {
                Toast.makeText(mContext, "stop " + selectBean.getName(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
