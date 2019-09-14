package com.seselin.diypanel.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    protected abstract void setLayoutView();

    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        RegisterEventBus();
        mContext = this;
        setLayoutView();
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
    }

    public void goActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void ToastShow(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    protected boolean useEventBus = false;

    protected void RegisterEventBus() {
        if (useEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    protected void unRegisterEventBus() {
        if (useEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

}
