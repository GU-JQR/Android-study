package com.example.jqr.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.jqr.entiyt.Food;

import java.util.ArrayList;
import java.util.List;

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

    //查询全部食品信息
    @SuppressLint("Range")
    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        //1.获取数据库查询操作对象（读操作）已经创建了mRDB 可以直接使用
        //2.利用mRDB查询
        Cursor cursor = mRDB.query(TABLE_NAME, null, null, null, null, null, null);
        //3.通过游标遍历查询的结构
        while (cursor.moveToNext()) {
            //4.构建每一条数据库中记录的实体对象food
            int _id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
            String foodname = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
            String noeatfood = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)));
            String picpath = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)));
            String foodesc = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)));
            //测试
            Log.d("JQR", _id + " " + foodname + " " + noeatfood + " " + picpath);
            //创建Food
            Food food = new Food(_id, foodname, noeatfood, picpath, foodesc);
            //5.将构建的实体对象Food添加到foodList集合中
            foodList.add(food);
        }
        //6.返回集合foodList
        return foodList;
    }

    //    根据食品名称关键字搜索对应的食品信息  例如：猪->相关猪的食品
//    sql: select * from table_food where foodname like '%猪%';
    @SuppressLint("Range")
    public List<Food> getFoodByName(String key) {
        //声明一个集合用了存放查询的数据
        List<Food> foodList = new ArrayList<>();
        //1.读操作 使用mRDB进行操作
//        Cursor cursor = mRDB.query(TABLE_NAME, new String[]{"_id", "foodname", "noeatfood", "picpath", "foodesc"}, "foodname like '%?%'", new String[]{key}, null, null, null);
        Cursor cursor = mRDB.rawQuery("select * from table_food where foodname like ?", new String[]{"%" + key + "%"});
        //2.通过查询得到游标遍历结果
        while (cursor.moveToNext()) {
            //4.构建每一条数据库中记录的实体对象food
            int _id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
            String foodname = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
            String noeatfood = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)));
            String picpath = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)));
            String foodesc = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)));
            //测试
            Log.d("JQR", _id + " " + foodname + " " + noeatfood + " " + picpath);
            //创建Food
            Food food = new Food(_id, foodname, noeatfood, picpath, foodesc);
            //5.将构建的实体对象Food添加到foodList集合中
            foodList.add(food);
        }
        //返回查询的结果集合
        return foodList;
    }
    //添加食品信息

}
