package com.yuansewenhua.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yuansewenhua.dao.DaoMaster;
import com.yuansewenhua.dao.DaoSession;

/**
 * Created by fangshuai on 2014-11-26-0026.
 */
public class DBUtils {

    /**
     * 初始化数据库，获得对应的dao对象实例
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        DaoMaster daoMaster = init(context);
        return daoMaster.newSession();
    }

    /**
     * 初始化数据库
     *
     * @param context
     * @return
     */
    public static DaoMaster init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "emenu-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        return new DaoMaster(db);
    }

    /**
     * 初始化数据库
     *
     * @param context
     * @param dbname
     * @return
     */
    public static DaoMaster init(Context context, String dbname) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, dbname, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        return new DaoMaster(db);
    }
}
