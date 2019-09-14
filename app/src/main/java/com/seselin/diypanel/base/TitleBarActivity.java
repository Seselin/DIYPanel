package com.seselin.diypanel.base;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.seselin.diypanel.R;


public abstract class TitleBarActivity extends BaseActivity {

    protected ImageView leftIv;
    protected ImageView rightIv;

    protected TextView titleTv;
    protected TextView rightTv;
    protected TextView leftTv;

    @Override
    protected void initView() {
        leftIv = findViewById(R.id.iv_title_left);
        if (leftIv == null)
            return;
        leftIv.setOnClickListener(v -> finish());
    }

    /**
     * 隐藏返回
     */
    protected void hideBack() {
        leftIv = findViewById(R.id.iv_title_left);
        if (leftIv == null)
            return;
        leftIv.setVisibility(View.GONE);
    }

    /**
     * 设置标题栏
     */
    protected void setTitleTv(String title) {
        titleTv = findViewById(R.id.tv_title);
        if (titleTv == null)
            return;
        titleTv.setText(title);
    }

    /**
     * 设置left图片
     */
    protected void setLeftIv(int resID) {
        leftIv = findViewById(R.id.iv_title_left);
        if (leftIv == null | resID == 0)
            return;
        leftIv.setImageResource(resID);
        leftIv.setOnClickListener(v -> finish());
    }

    /**
     * 设置left文字
     */
    protected void setLeftTv(String text) {
        leftTv = findViewById(R.id.tv_title_left);
        leftIv = findViewById(R.id.iv_title_left);
        if (leftTv == null || TextUtils.isEmpty(text))
            return;
        if (leftIv != null)
            leftIv.setVisibility(View.GONE);
        leftTv.setVisibility(View.VISIBLE);
        leftTv.setText(text);
    }

    /**
     * 设置right图片
     */
    protected void setRightIv(int resID) {
        rightIv = findViewById(R.id.iv_title_right);
        rightTv = findViewById(R.id.tv_title_right);
        if (rightIv == null || resID == 0) {
            rightIv.setVisibility(View.GONE);
            return;
        }
        if (rightTv != null)
            rightTv.setVisibility(View.GONE);
        rightIv.setVisibility(View.VISIBLE);
        rightIv.setImageResource(resID);
    }

    /**
     * 设置right文字
     */
    protected void setRightTv(String text) {
        rightIv = findViewById(R.id.iv_title_right);
        rightTv = findViewById(R.id.tv_title_right);
        if (rightTv == null || TextUtils.isEmpty(text))
            return;
        if (rightIv != null)
            rightIv.setVisibility(View.GONE);
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText(text);
    }

}
