package com.seselin.diypanel.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.SimpleSelectAdapter;
import com.seselin.diypanel.bean.SelectBean;

import java.util.ArrayList;


/**
 * 单项选择
 */
public abstract class BaseSelectActivity extends AppCompatActivity {

    protected Context mContext;
    protected TextView tvTitle;
    protected ListView listView;

    protected SimpleSelectAdapter adapter;
    protected ArrayList<SelectBean> selectBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_simple_select);

        tvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.iv_title_left).setOnClickListener(v -> finish());
        listView = findViewById(R.id.listView);

        adapter = new SimpleSelectAdapter(mContext);
        adapter.setDataList(selectBeans);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            SelectBean bean = selectBeans.get(position);
            setSelectBean(bean);
        });

        setTitle();

        selectBeans.clear();
        initData();
    }

    protected abstract void setTitle();

    protected abstract void initData();

    protected abstract void setSelectBean(SelectBean selectBean);

}
