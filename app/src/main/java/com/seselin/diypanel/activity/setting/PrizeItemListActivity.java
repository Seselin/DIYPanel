package com.seselin.diypanel.activity.setting;

import android.os.Message;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.PrizeListAdapter;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Seselin on 2019/9/14.
 */
public class PrizeItemListActivity extends TitleBarActivity {

    {
        useEventBus = true;
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_bottom)
    Button btnBottom;

    PrizeListAdapter adapter;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_list_with_button);
    }

    @Override
    protected void initView() {
        super.initView();

        setTitleTv("奖品库编辑");
        btnBottom.setText("新增奖品");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PrizeListAdapter(DataUtil.getPrizeAllData());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            PrizeBean bean = (PrizeBean) adapter.getItem(position);
            PrizeItemEditActivity.load(bean);
        });
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_bottom)
    void AddItem() {
        goActivity(PrizeItemEditActivity.class);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Message message) {
        switch (message.what) {
            case EventBusTag.PRIZE_DATA_CHANGE:
                adapter.setData(DataUtil.getPrizeAllData());
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

}
