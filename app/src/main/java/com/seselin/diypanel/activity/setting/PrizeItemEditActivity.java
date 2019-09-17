package com.seselin.diypanel.activity.setting;

import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.seselin.diypanel.R;
import com.seselin.diypanel.base.TitleBarActivity;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.tag.Color;
import com.seselin.diypanel.tag.EventBusTag;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.util.InputUtil;

import butterknife.BindView;

/**
 * Created by Seselin on 2019/9/14.
 */
@Route(path = "/diypanel/PrizeItemEditActivity")
public class PrizeItemEditActivity extends TitleBarActivity {

    @Autowired
    PrizeBean prizeBean;

    public static void load(PrizeBean bean) {
        ARouter.getInstance().build("/diypanel/PrizeItemEditActivity")
                .withObject("prizeBean", bean)
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
        setRightTv("提交");
        rightTv.setTextColor(Color.BLUE);
        rightTv.setOnClickListener(view -> {
            submit();
        });

        if (prizeBean != null) {
            etName.setText(prizeBean.getName());
            etWeight.setText(String.format("%d", prizeBean.getWeight()));
        }
    }

    private void submit() {
        if (checkInput()) {
            DataUtil.addPrizeBean(prizeBean);
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
