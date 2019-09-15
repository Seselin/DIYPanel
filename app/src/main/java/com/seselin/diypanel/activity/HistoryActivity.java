package com.seselin.diypanel.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.greendao.gen.DaoSession;
import com.seselin.diypanel.R;
import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.tag.Color;

import butterknife.BindView;

/**
 * Created by Seselin on 2019/8/21.
 */
public class HistoryActivity extends TitleBarActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
            // 删除选项
            DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
            mDaoSession.getHistoryBeanDao().deleteAll();
        });

    }

}
