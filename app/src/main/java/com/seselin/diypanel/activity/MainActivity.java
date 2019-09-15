package com.seselin.diypanel.activity;

import android.os.Message;
import android.widget.TextView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.activity.setting.SettingActivity;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.HistoryBean;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.bean.SelectBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.util.SettingConfig;
import com.seselin.diypanel.view.PanelView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Seselin on 2019/7/2.
 */
public class MainActivity extends TitleBarActivity {

    {
        useEventBus = true;
    }


    @BindView(R.id.tv_result)
    TextView tvResult;

    @BindView(R.id.panel_view)
    PanelView panelView;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleTv("主页");
        setLeftTv("历史记录");
        setRightTv("设置");

        panelView = findViewById(R.id.panel_view);

        initPanel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        panelView.stopGame();
    }

    private void setPanelData() {
        SelectBean selectBean = SettingConfig.getGridNum();
        int spanCount = Integer.parseInt(selectBean.getValue());
        panelView.initPanelData(spanCount, DataUtil.getPrizeData());
    }

    private void initPanel() {
        setPanelData();
        panelView.setPanelListener(new PanelView.PanelListener() {

            @Override
            public void onStart(PrizeBean selectBean) {
                tvResult.setText(selectBean.getName());
                HistoryBean historyBean = new HistoryBean();
                historyBean.setTime(new Date().getTime());
                historyBean.setPrizeBean(selectBean);
                DataUtil.addHistoryBean(historyBean);
            }

            @Override
            public void onStop(PrizeBean selectBean) {
//                ToastShow("stop " + selectBean.getName());
            }
        });
    }

    @OnClick(R.id.tv_title_right)
    void goSetting() {
        goActivity(SettingActivity.class);
    }

    @OnClick(R.id.tv_title_left)
    void goHistory() {
        // TODO 跳转历史记录
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Message message) {
        switch (message.what) {
            case EventBusTag.SET_GRID_NUM:
            case EventBusTag.PRIZE_DATA_CHANGE:
                setPanelData();
                break;
            default:
                break;
        }
    }

}
