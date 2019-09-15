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

    public void setData(List<HistoryBean> datas) {
        mData.clear();
        mData.addAll(datas);
    }

    public HistoryListAdapter(List<HistoryBean> datas) {
        super(R.layout.item_prize_bean, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, HistoryBean bean) {
        String time = DateUtils.getTimeStr(bean.getTime());
        String name = bean.getPrizeBean().getName();
        holder.setText(R.id.tv_name, time + "," + name);
    }

}
