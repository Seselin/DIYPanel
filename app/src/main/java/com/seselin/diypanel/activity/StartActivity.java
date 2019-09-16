package com.seselin.diypanel.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.seselin.diypanel.activity.setting.SettingActivity;
import com.seselin.diypanel.base.BaseApplication;
import com.seselin.diypanel.util.ApkTools;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.util.SettingConfig;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNoticeDialog();
    }

    private void goStartActivity() {
        boolean test = false;
//        test = true;
        if (test) {// 启动测试页
            goActivity(SettingActivity.class);
            return;
        }

        goActivity(MainActivity.class);
    }

    public void goActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkAppPermission();
    }

    private void startApp() {
        BaseApplication.getInstance().initDatabase();
        if (SettingConfig.isFirstOpen()) {
            DataUtil.initData();
            SettingConfig.clearFirstOpen();
        }
        goStartActivity();
        finish();
    }

    private final int PERMISSION_REQUEST = 0xa00;

    public static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void checkAppPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST);
        } else {
            startApp();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST:
                for (int grantResult : grantResults) {
                    if (grantResult == -1) {
                        showNoticeDialog();
                        return;
                    }
                }
                break;
        }
        startApp();
    }

    private AlertDialog permissionDialog;

    private void showNoticeDialog() {
        if (permissionDialog != null && !permissionDialog.isShowing()) {
            permissionDialog.show();
        }
    }

    private void initNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("权限不足,请到应用管理打开权限。")
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ApkTools.openAppInfo(StartActivity.this);
                }).setNegativeButton("取消", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    finish();
                });
        permissionDialog = builder.create();
        permissionDialog.setCanceledOnTouchOutside(false);
        permissionDialog.setCancelable(false);
    }

}
