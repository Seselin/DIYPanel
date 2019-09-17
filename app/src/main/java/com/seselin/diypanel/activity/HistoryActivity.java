package com.seselin.diypanel.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.HistoryListAdapter;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.HistoryBean;
import com.seselin.diypanel.tag.Color;
import com.seselin.diypanel.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Seselin on 2019/8/21.
 */
public class HistoryActivity extends TitleBarActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    HistoryListAdapter adapter;
    List<HistoryBean> dataList = new ArrayList<>();
    int page = 0;
    int offset = 10;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_history_list);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleTv("抽奖记录");
        setRightTv("清空记录");
        rightTv.setTextColor(Color.RED);
        rightTv.setOnClickListener(view -> {
            DataUtil.clearHistoryData();
            loadFistPage();
        });

        adapter = new HistoryListAdapter(dataList);
        adapter.setOnLoadMoreListener(() -> getData(), recyclerView);

        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            adapter.setEnableLoadMore(true);
            loadFistPage();
            refreshLayout.finishRefresh(500);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        loadFistPage();
    }

    private void loadFistPage() {
        this.page = 0;
        dataList.clear();
        getData();
    }

    private void getData() {
        List<HistoryBean> newDataList = DataUtil.getHistoryDataByPage(page, offset);
        dataList.addAll(newDataList);
        adapter.notifyDataSetChanged();
        if (newDataList.size() >= offset) {
            adapter.loadMoreComplete();
        } else {
            adapter.loadMoreEnd();
        }
        this.page++;
    }

}
