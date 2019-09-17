package com.seselin.diypanel.window;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.SelectMenuAdapter;
import com.seselin.diypanel.bean.CheckBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Seselin on 2019/9/16 13:10.
 */
public class SelectWindow extends Dialog {

    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitleName;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SelectMenuAdapter menuAdapter;

    private OnMenuListener listener;

    public interface OnMenuListener {
        void onConfirmClick(String selectKey);
    }

    public void setOnMenuListener(OnMenuListener listener) {
        this.listener = listener;
    }

    public SelectWindow(Context context, ArrayList<CheckBean> dataList) {
        super(context, R.style.MenuDialogStyle);
        View menuView = LayoutInflater.from(context).inflate(R.layout.window_select, null);
        Window window = this.getWindow();
        window.setContentView(menuView);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        ButterKnife.bind(this);

        ArrayList<CheckBean> dataList1 = new ArrayList<>();
        dataList1.clear();
        dataList1.addAll(dataList);

        menuAdapter = new SelectMenuAdapter(dataList1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(menuAdapter);
        initListener();
    }

    private void initListener() {
        tvTitleName.setText("请选择");
        ivTitleLeft.setOnClickListener(v -> dismiss());

        menuAdapter.setOnItemClickListener((adapter, view, position) -> {
            CheckBean bean = (CheckBean) adapter.getItem(position);
            if (listener != null) {
                listener.onConfirmClick(bean.getKey());
            }
            dismiss();
        });
    }

}

