package com.seselin.diypanel.adapter;

import android.content.Context;
import android.view.View;

import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.PrizeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class GridAdapter extends CommonAdapter<PrizeBean> {


    public GridAdapter(Context context, List<PrizeBean> datas) {
        super(context, R.layout.view_grild_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, PrizeBean bean, int position) {
        holder.setText(R.id.text, bean.getName());
        holder.getView(R.id.layout_view).setVisibility(bean.isShow() ? View.VISIBLE : View.INVISIBLE);
        holder.getView(R.id.overlay).setVisibility(bean.isFocus() ? View.INVISIBLE : View.VISIBLE);
    }

}
