package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jqr.adapter.MyAdapter;
import com.example.jqr.entiyt.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    //声明控件
    ImageView iv_back;
    private List<Food> myDatas;
    //声明适配器
    private MyAdapter myAdapter;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //获取控件
        iv_back = findViewById(R.id.iv_back);
        lv = findViewById(R.id.lv);
        //绑定点击监听
        iv_back.setOnClickListener(this);

        //ListView相关  创建数据源
        myDatas = new ArrayList<>();
        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));

        //创建适配器
        myAdapter = new MyAdapter(myDatas, this);

        //绑定ListView适配器
        lv.setAdapter(myAdapter);
    }

    //全局点击处理方法
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                //返回
                finish();
                break;
        }
    }
}