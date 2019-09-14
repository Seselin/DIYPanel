package com.seselin.diypanel.tag;

import android.os.Message;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Seselin on 2019/9/14.
 * EventBus的标志
 */
public class EventBusTag {

    public static final int SET_GRID_NUM = 0x01;//设置网格的行数

    public static void post(int tag) {
        Message message = Message.obtain();
        message.what = tag;
        EventBus.getDefault().post(message);
    }

    public static void post(int tag, Object object) {
        Message message = Message.obtain();
        message.what = tag;
        message.obj = object;
        EventBus.getDefault().post(message);
    }

}
