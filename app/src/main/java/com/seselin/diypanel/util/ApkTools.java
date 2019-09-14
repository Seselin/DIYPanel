package com.seselin.diypanel.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Seselin on 2016/10/9 15:14.
 * <p>
 * Apk相关工具类
 */

public class ApkTools {

    /**
     * 获取Apk的编译时间
     *
     * @param context 上下文
     * @return 编译时间
     */
    public static String getBuildTime(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String buildTime = "";
        try {
            buildTime = packageManager.getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA).metaData.getString("buildTime");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return buildTime;
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String version = "";
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String version = "";
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            version = packInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }


    /**
     * 打开当前应用详情
     *
     * @param context 上下文
     */
    public static void openAppInfo(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 验证密码字母跟数字混合
    public static Boolean checkPwdstrict(String password) {
        if (password.length() >= 6 && password.length() <= 16) {
            Pattern p1 = Pattern.compile("[a-zA-Z]+");
            Pattern p2 = Pattern.compile("[0-9]+");
            Matcher m = p1.matcher(password);
            if (!m.find())
                return false;
            else {
                m.reset().usePattern(p2);
                if (!m.find())
                    return false;
                else
                    return true;
            }
        } else {
            return false;
        }
    }

    // 验证密码
    public static Boolean checkPwd(String password) {
        if (password.length() >= 6 && password.length() <= 16) {
            return true;
        } else {
            return false;
        }
    }

    // 验证密码
    public static Boolean checkPwd(EditText etPassword) {
        String password = etPassword.getText().toString().trim();
        String errorStr;
        String pattern;
        boolean isMatch;

        if (password.length() < 6 || password.length() > 16) {
            errorStr = "请输入6至16位的密码";
            etPassword.setError(errorStr);
            etPassword.requestFocus();
            return false;
        }

        pattern = "^.*[A-Za-z].*[0-9].*$|^.*[0-9].*[A-Za-z].*$";
        isMatch = Pattern.matches(pattern, password);
        if (!isMatch) {
            errorStr = "密码必须同时包含 字母、数字";
            etPassword.setError(errorStr);
            etPassword.requestFocus();
            return false;
        }

        pattern = "^.*(.)\\1{2,}+.*$";
        isMatch = Pattern.matches(pattern, password);
        if (isMatch) {
            errorStr = "密码连续一致的字符不能超过3个";
            etPassword.setError(errorStr);
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * 解决小米手机上获取图片路径为null的情况
     *
     * @param intent
     * @return
     */
    public static Uri getUri(android.content.Intent intent, Context context) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }

}
