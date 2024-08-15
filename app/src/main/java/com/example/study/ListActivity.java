package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.study.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    //数据源 集合
    List<Person> person = new ArrayList<>();
//    有一个适配器(自定义)

    //声明活动界面的ListView控件
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        获取数据
        for (int i = 0; i < 100; i++) {
            Person p = new Person("姓名" + i, i);
            person.add(p);
        }
        //获取控件
        lv = findViewById(R.id.lv);
    }
}