package com.seselin.diypanel.activity.setting;

import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.PrizeListAdapter;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.CheckBean;
import com.seselin.diypanel.bean.PlanBean;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.tag.Color;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.util.InputUtil;
import com.seselin.diypanel.window.CheckWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Seselin on 2019/9/17.
 */
@Route(path = "/diypanel/PlanEditActivity")
public class PlanEditActivity extends TitleBarActivity {

    @Autowired
    PlanBean planBean;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    PrizeListAdapter adapter;
    ArrayList<String> keyList = new ArrayList<>();

    public static void load(PlanBean bean) {
        ARouter.getInstance().build("/diypanel/PlanEditActivity")
                .withObject("planBean", bean)
                .navigation();
    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_plan_edit);
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        super.initView();

        setTitleTv("奖品编辑");
        setRightTv("提交");
        rightTv.setTextColor(Color.BLUE);
        rightTv.setOnClickListener(view -> {
            submit();
        });

        if (planBean != null) {
            etName.setText(planBean.getName());
            keyList = planBean.getPrizeList();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PrizeListAdapter(DataUtil.getPrizeDataByKey(keyList));
        adapter.setOnItemClickListener((adapter, view, position) -> {
            PrizeBean bean = (PrizeBean) adapter.getItem(position);
            PrizeItemEditActivity.load(bean);
        });
        recyclerView.setAdapter(adapter);
    }

    private void submit() {
        if (checkInput()) {
            DataUtil.addPlanBean(planBean);
            EventBusTag.send(EventBusTag.PRIZE_PLAN_CHANGE);
            finish();
        }
    }

    private boolean checkInput() {
        if (InputUtil.checkInput(etName)) {
            return false;
        }
        if (keyList.size() < 3) {
            ToastShow("奖品数不能少于3！");
            return false;
        }
        if (planBean == null) {
            planBean = new PlanBean();
        }
        String name = etName.getText().toString().trim();
        planBean.setName(name);
        planBean.setPrizeList(keyList);
        return true;
    }

    @OnClick(R.id.tv_set_prize_list)
    void selectPrizeList() {
        ArrayList<PrizeBean> dataList = (ArrayList<PrizeBean>) DataUtil.getPrizeAllData();
        ArrayList<CheckBean> checkList = new ArrayList<>();
        for (PrizeBean prizeBean : dataList) {
            String key = String.format("%d", prizeBean.getId());

            CheckBean checkBean = new CheckBean()
                    .setKey(key)
                    .setName(prizeBean.getName())
                    .setCheck(keyList.contains(key));
            checkList.add(checkBean);
        }
        CheckWindow checkWindow = new CheckWindow(mContext, checkList);
        checkWindow.setOnMenuListener(selectKeyList -> {
            this.keyList.clear();
            this.keyList.addAll(selectKeyList);

            adapter.setData(DataUtil.getPrizeDataByKey(keyList));
            adapter.notifyDataSetChanged();
        });
        checkWindow.show();
    }

}
