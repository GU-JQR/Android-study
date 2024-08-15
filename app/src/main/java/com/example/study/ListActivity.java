package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.study.adapter.MyAdapter;
import com.example.study.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    //数据源 集合
    List<Person> persons = new ArrayList<>();
    //有一个适配器(自定义)
    MyAdapter adapter;
    //声明活动界面的ListView控件
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        获取数据
        for (int i = 0; i < 100; i++) {
            Person p = new Person("姓名" + i, i);
            persons.add(p);
        }
        //获取控件
        lv = findViewById(R.id.lv);
        adapter = new MyAdapter(persons, this);
        //ListView绑定适配器
        lv.setAdapter(adapter);

        //为了详情界面能够获取信息 每一个项目可以添加点击监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * 点击每一项会执行以下方法
             * @param parent The AdapterView where the click happened.
             * @param view The view within the AdapterView that was clicked (this
             *            will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent + " " + view + "  " + position + "  " + id);
            }
        });
    }
}