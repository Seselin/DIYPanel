package com.seselin.diypanel.util;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 用来保存和读取对象
 * <p/>
 * Created by Seselin on 2016/9/30.
 */
public class HistoryUtil {


    /**
     * 保存类
     * HistoryUtil.saveObject(mContext, "area", data);
     *
     * @param context 上下文
     * @param key     键值
     * @param object  类对象
     */
    public static void saveObject(Context context, String key, Object object) {
        Gson gson = new Gson();
        String beanStr = gson.toJson(object);
        SPUtils.putString(context, key, beanStr);
    }

    /**
     * 保存类
     * HistoryUtil.saveHasNullObject(mContext, "area", data);
     *
     * @param context 上下文
     * @param key     键值
     * @param object  类对象
     */
    public static void saveHasNullObject(Context context, String key, Object object) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String beanStr = gson.toJson(object);
        SPUtils.putString(context, key, beanStr);
    }

    /**
     * 读取类
     * AreaVo areaVo = HistoryUtil.loadObject(mContext, "area", AreaVo.class);
     *
     * @param context  上下文
     * @param key      键值
     * @param classOfT 类名
     * @param <T>      the type of the desired object
     * @return 类内容
     */
    public static <T> T loadObject(Context context, String key, Class<T> classOfT) {
        String json = SPUtils.getString(context, key, "");
        if (TextUtils.isEmpty(json))
            return null;
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    /**
     * 清空保存
     *
     * @param context 上下文
     * @param key     键值
     */
    public static void cleanObject(Context context, String key) {
        SPUtils.putString(context, key, "");
    }

}
