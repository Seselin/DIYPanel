package com.seselin.diypanel.activity.setting;

import android.os.Message;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.PlanListAdapter;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.PlanBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Seselin on 2019/9/14.
 */
public class PlanListActivity extends TitleBarActivity {

    {
        useEventBus = true;
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_bottom)
    Button btnBottom;

    PlanListAdapter adapter;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_list_with_button);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleTv("方案列表");
        btnBottom.setText("新增方案");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlanListAdapter(DataUtil.getPlanList());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            PlanBean planBean = (PlanBean) adapter.getItem(position);
            PlanEditActivity.load(planBean);
        });
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_bottom)
    void AddPlan() {
        goActivity(PlanEditActivity.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Message message) {
        switch (message.what) {
            case EventBusTag.PRIZE_PLAN_CHANGE:
                adapter.setData(DataUtil.getPlanList());
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

}
