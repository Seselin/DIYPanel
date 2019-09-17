package com.seselin.diypanel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.HistoryBean;
import com.seselin.diypanel.util.DateUtils;

import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class HistoryListAdapter extends BaseQuickAdapter<HistoryBean, BaseViewHolder> {

    public HistoryListAdapter(List<HistoryBean> datas) {
        super(R.layout.item_history_bean, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, HistoryBean bean) {
        String name = "奖品：" + bean.getPrizeBean().getName();
        String time = "时间：" + DateUtils.getTimeStr(bean.getTime());
        holder.setText(R.id.tv_name, name + "\n" + time);
    }

}
