package com.seselin.diypanel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.PlanBean;

import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class PlanListAdapter extends BaseQuickAdapter<PlanBean, BaseViewHolder> {

    public void setData(List<PlanBean> datas) {
        mData.clear();
        mData.addAll(datas);
    }

    public PlanListAdapter(List<PlanBean> datas) {
        super(R.layout.item_simple_bean, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, PlanBean bean) {
        holder.setText(R.id.tv_name, bean.getName());
    }

}
