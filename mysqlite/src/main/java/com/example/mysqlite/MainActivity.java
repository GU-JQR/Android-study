package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import util.DBHelper;

public class MainActivity extends AppCompatActivity {

    //声明DBHelper工具类对象
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动数据库
        dbHelper = DBHelper.getInstance(this);
        //获取可读数据库（执行这一步才会走进 onCreate(SQLiteDatabase db)）
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        //创建的数据库位置  打开Device File Explorer
        //data--data-- 项目同报名的文件夹-->databases文件夹
    }
}