package com.seselin.diypanel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seselin.diypanel.R;
import com.seselin.diypanel.activity.setting.PrizeItemEditActivity;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;

import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class PrizeListAdapter extends BaseQuickAdapter<PrizeBean, BaseViewHolder> {

    public void setData(List<PrizeBean> datas) {
        mData.clear();
        mData.addAll(datas);
    }

    public PrizeListAdapter(List<PrizeBean> datas) {
        super(R.layout.item_swipe_bean, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, PrizeBean bean) {
        holder.setText(R.id.tv_name, bean.getName());
        holder.getView(R.id.tv_delete).setOnClickListener(view -> {
            DataUtil.deletePrizeBean(bean.getId());
            int position = holder.getAdapterPosition();
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());

            EventBusTag.send(EventBusTag.PRIZE_DATA_CHANGE);
        });
        holder.getView(R.id.content).setOnClickListener(view -> PrizeItemEditActivity.load(bean));
    }

}
