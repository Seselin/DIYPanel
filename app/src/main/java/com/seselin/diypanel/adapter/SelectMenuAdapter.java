package com.seselin.diypanel.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.CheckBean;

import java.util.List;

/**
 * Created by Seselin on 2019/9/16.
 */
public class SelectMenuAdapter extends BaseQuickAdapter<CheckBean, BaseViewHolder> {


    public SelectMenuAdapter(@Nullable List<CheckBean> data) {
        super(R.layout.item_simple_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckBean item) {
        helper.setText(R.id.tv_select, item.getName());
    }

}
