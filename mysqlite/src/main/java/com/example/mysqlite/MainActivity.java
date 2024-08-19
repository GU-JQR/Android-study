package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mysqlite.entity.Person;
import com.example.mysqlite.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //声明DBHelper工具类对象
    private DBHelper dbHelper;

    //声明控件
    Button btn_createDB, btn_insert, btn_queryall, btn_delete, btn_updata;

    //声明数据库读写对象
    SQLiteDatabase rDB;
    SQLiteDatabase wDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_createDB = findViewById(R.id.btn_createDB);
        btn_insert = findViewById(R.id.btn_insert);
        btn_queryall = findViewById(R.id.btn_queryall);
        btn_delete = findViewById(R.id.btn_delete);
        btn_updata = findViewById(R.id.btn_updata);

        btn_createDB.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_queryall.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_updata.setOnClickListener(this);


        //启动数据库
        dbHelper = DBHelper.getInstance(this);
        //获取可读数据库（执行这一步才会走进 onCreate(SQLiteDatabase db)）
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        //创建的数据库位置  打开Device File Explorer
        //data--data-- 项目同报名的文件夹-->databases文件夹
        Log.d("ZHX", "create DB ...");
        //获取可读数据库
        rDB = dbHelper.getReadableDatabase();
        //获取写数据权限
        wDB = dbHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_createDB:

//                SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();


                //创建的数据库位置  打开Device File Explorer
                //data--data-- 项目同报名的文件夹-->databases文件夹
                break;
            case R.id.btn_insert:
                Log.d("ZHX", "insert data ...");
                ContentValues values = new ContentValues();
                values.put("name", "jqr");
                //利用写数据库对象
                wDB.insert("person", null, values);
                break;
            case R.id.btn_queryall:
                //查询（读操作）rDB  查询结果做一个封装 封装到一个Person的对象中

                List<Person> personList = new ArrayList<>();
                Cursor cursor = rDB.query("person", null, null, null, null, null, null);
//                使用游标遍历集合中的每一项，游标下移
                while (cursor.moveToNext()) {
                    //如何获取一条记录
                    @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    //封装到person对象
                    Person p = new Person(_id, name);
                    //把学生添加到集合中
                    personList.add(p);
                }

                for (int i = 0; i < personList.size(); i++) {
                    System.out.println(personList.get(i).toString());
                }
                break;
            //删除(写操作)
            case R.id.btn_delete:
                int row = wDB.delete("person", "_id=?", new String[]{"1"});
                //如果删除结果不为0，表示删除了多少条记录
                System.out.println(row > 0 ? "删除成功" : "删除失败");

                break;

            case R.id.btn_updata:
                ContentValues values2 = new ContentValues();
                values2.put("name", "xhd");
                int row2 = wDB.update("person", values2, "name=?", new String[]{"csl"});
                System.out.println(row2 > 0 ? "修改成功" : "修改失败");
        }
    }
}