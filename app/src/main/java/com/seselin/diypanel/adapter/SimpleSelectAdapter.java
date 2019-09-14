package com.seselin.diypanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.seselin.diypanel.R;
import com.seselin.diypanel.bean.SelectBean;

import java.util.ArrayList;


public class SimpleSelectAdapter extends BaseAdapter {

    @ColorInt
    public static final int SERVER_SELECT = 0xFF007FFF;
    @ColorInt
    public static final int SERVER_COMMON = 0xFF2C3949;

    private Context context;
    private ArrayList<SelectBean> dataList;

    public SimpleSelectAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(ArrayList<SelectBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        if (dataList == null) {
            return null;
        }
        return dataList.get(position).getName();
    }

    @Override
    public int getCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_simple_select, parent, false);
        SelectBean serverBean = dataList.get(position);

        TextView tvSelect = convertView.findViewById(R.id.tv_select);
        int textColor = serverBean.isCheck() ? SERVER_SELECT : SERVER_COMMON;
        tvSelect.setTextColor(textColor);
        tvSelect.setText(serverBean.getName());
        return convertView;
    }

}
