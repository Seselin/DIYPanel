package com.seselin.diypanel.adapter;

import android.content.Context;

import com.seselin.diypanel.R;
import com.seselin.diypanel.activity.setting.PrizeItemEditActivity;
import com.seselin.diypanel.bean.PrizeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class PrizeListAdapter extends CommonAdapter<PrizeBean> {

    public void setData(List<PrizeBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
    }

    public PrizeListAdapter(Context context, List<PrizeBean> datas) {
        super(context, R.layout.item_prize_bean, datas);
    }

    @Override
    protected void convert(ViewHolder holder, PrizeBean bean, int position) {
        holder.setText(R.id.tv_name, bean.getName());
        holder.getConvertView().setOnClickListener(view -> PrizeItemEditActivity.load(bean));
    }

}
