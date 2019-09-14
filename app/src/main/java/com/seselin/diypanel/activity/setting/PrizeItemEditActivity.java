package com.seselin.diypanel.activity.setting;

import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.greendao.gen.DaoSession;
import com.seselin.diypanel.R;
import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.tag.Color;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.InputUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Seselin on 2019/9/14.
 */
@Route(path = "/diypanel/PrizeItemEditActivity")
public class PrizeItemEditActivity extends TitleBarActivity {

    @Autowired
    PrizeBean prizeBean;

    public static void load(PrizeBean prizeBean) {
        ARouter.getInstance().build("/diypanel/PrizeItemEditActivity")
                .withObject("prizeBean", prizeBean)
                .navigation();
    }


    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_weight)
    EditText etWeight;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_prize_item_edit);
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        super.initView();
        setTitleTv("奖品编辑");
        if (prizeBean != null) {
            etName.setText(prizeBean.getName());
            etWeight.setText(String.format("%d", prizeBean.getWeight()));

            setRightTv("删除");
            rightTv.setTextColor(Color.RED);
            rightTv.setOnClickListener(view -> {
                // 删除选项
                DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
                mDaoSession.getPrizeBeanDao().deleteByKey(prizeBean.getId());
                notifyPrizeChange();
                finish();
            });
        }
    }

    @OnClick(R.id.btn_submit)
    void submit() {
        if (checkInput()) {
            DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
            mDaoSession.getPrizeBeanDao().insertOrReplace(prizeBean);
            notifyPrizeChange();
            finish();
        }
    }

    private boolean checkInput() {
        if (InputUtil.checkInput(etName)) {
            return false;
        }


        if (InputUtil.checkInput(etWeight)) {
            return false;
        }

        if (prizeBean == null) {
            prizeBean = new PrizeBean();
        }

        String name = etName.getText().toString().trim();
        int weight = Integer.parseInt(etWeight.getText().toString().trim());
        prizeBean.setName(name);
        prizeBean.setWeight(weight);

        return true;
    }

    private void notifyPrizeChange() {
        EventBusTag.send(EventBusTag.PRIZE_DATA_CHANGE);
    }

}
