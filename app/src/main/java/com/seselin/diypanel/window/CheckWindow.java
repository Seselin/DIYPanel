package com.seselin.diypanel.window;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.CheckMenuAdapter;
import com.seselin.diypanel.bean.CheckBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Seselin on 2019/9/16 13:10.
 */
public class CheckWindow extends Dialog {

    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitleName;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_bottom)
    Button btnBottom;

    private CheckMenuAdapter menuAdapter;

    private OnMenuListener listener;

    public interface OnMenuListener {
        void onConfirmClick(ArrayList<String> selectKeyList);
    }

    public void setOnMenuListener(OnMenuListener listener) {
        this.listener = listener;
    }

    public CheckWindow(Context context, ArrayList<CheckBean> dataList) {
        super(context, R.style.MenuDialogStyle);
        View menuView = LayoutInflater.from(context).inflate(R.layout.pop_window_check_list, null);
        Window window = this.getWindow();
        window.setContentView(menuView);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        ButterKnife.bind(this);

        ArrayList<CheckBean> dataList1 = new ArrayList<>();
        dataList1.clear();
        dataList1.addAll(dataList);

        menuAdapter = new CheckMenuAdapter(R.layout.item_list_check, dataList1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(menuAdapter);
        initListener();
    }

    private void initListener() {
        tvTitleName.setText("请选择");
        ivTitleLeft.setOnClickListener(v -> dismiss());

        menuAdapter.setOnItemClickListener((adapter, view, position) -> {
            ArrayList<CheckBean> dataList = (ArrayList<CheckBean>) menuAdapter.getData();
            boolean isCheck = !dataList.get(position).isCheck();
            dataList.get(position).setCheck(isCheck);
            menuAdapter.notifyDataSetChanged();
        });

        btnBottom.setOnClickListener(v -> {
            if (listener != null) {
                ArrayList<String> selectKeyList = new ArrayList<>();
                ArrayList<CheckBean> dataList = (ArrayList<CheckBean>) menuAdapter.getData();
                for (CheckBean checkBean : dataList) {
                    if (checkBean.isCheck()) {
                        String key = checkBean.getKey();
                        selectKeyList.add(key);
                    }
                }
                listener.onConfirmClick(selectKeyList);
            }
            dismiss();
        });
    }

}

