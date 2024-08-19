package com.example.jqr.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //声明静态常亮  静态常量
    private static final String DB_NAME = "SQU21_FOOD_DB";
    private static final String TABLE_NAME = "table_food";
    private static final int VERSION_ID = 1;
    //声明读写数据库操作对象
    private SQLiteDatabase mRDB;
    private SQLiteDatabase mWDB;

    //声明静态本类对象
    private static DBHelper instance;

    private DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //公共获取本类单例方法
    public static DBHelper getInstance(Context context) {
        //判断本类的实例还不存在
        if (instance == null) {
            instance = new DBHelper(context, DB_NAME, null, VERSION_ID);
        }
        return instance;
    }

    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = instance.getReadableDatabase();
        }
        return mRDB;
    }

    public SQLiteDatabase openwriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = instance.getReadableDatabase();
        }
        return mWDB;
    }

    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }
        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("JQR", "数据库Create...");
        //表中的字段  _id   foodname  noeatfood   picpath  foodesc
        String sql = "create table if not exists " + TABLE_NAME + "(_id integer primary key autoincrement not null," +
                "foodname varchar not null," +
                "noeatfood varchar not null," +
                "picpath varchar not null," +
                "fooddesc varchar not null)";
        db.execSQL(sql);
        Log.d("JQR", "CREATE TABLE SUCCESS...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("JQR", "数据库更新...");
    }

}
