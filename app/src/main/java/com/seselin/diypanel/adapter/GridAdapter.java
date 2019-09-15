package com.seselin.diypanel.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.PrizeUIBean;

import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class GridAdapter extends BaseQuickAdapter<PrizeUIBean, BaseViewHolder> {

    public GridAdapter(List<PrizeUIBean> datas) {
        super(R.layout.view_grild_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, PrizeUIBean bean) {
        helper.setText(R.id.text, bean.getName());
        helper.getView(R.id.layout_view).setVisibility(bean.isShow() ? View.VISIBLE : View.INVISIBLE);
        helper.getView(R.id.overlay).setVisibility(bean.isFocus() ? View.INVISIBLE : View.VISIBLE);
    }

}
