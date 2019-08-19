package com.seselin.diypanel.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.seselin.diypanel.BuildConfig;
import com.seselin.diypanel.util.CrashHandler;


public class BaseApplication extends Application {

    private static boolean debug;

    public static boolean isDebug() {
        return debug;
    }

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        debug = true;
        if (!BuildConfig.DEBUG) {
            debug = false;
        }

    }

    private DaoSession mDaoSession;

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void initDatabase() {
        try {
            String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/seselin";
            String dbFilename = DATABASE_PATH + "/bean.db";
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(
                    getApplicationContext(), dbFilename, null);
            SQLiteDatabase db = devOpenHelper.getWritableDatabase();
            DaoMaster mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
