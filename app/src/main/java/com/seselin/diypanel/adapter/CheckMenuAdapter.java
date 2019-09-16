package com.seselin.diypanel.adapter;

import android.widget.CheckBox;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.CheckBean;

import java.util.List;

/**
 * Created by Seselin on 2019/9/16.
 */
public class CheckMenuAdapter extends BaseQuickAdapter<CheckBean, BaseViewHolder> {


    public CheckMenuAdapter(int layoutResId, @Nullable List<CheckBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckBean item) {
        helper.setText(R.id.tv_left, item.getName());
        CheckBox checkBox = helper.getView(R.id.item_check_box);
        checkBox.setChecked(item.isCheck());
    }

}
