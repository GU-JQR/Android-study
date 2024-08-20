package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jqr.adapter.MyAdapter;
import com.example.jqr.entiyt.Food;
import com.example.jqr.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper dbHelper;
    //声明控件
    ImageView iv_back, iv_search_btn, iv_flush;
    EditText et_search;
    private List<Food> myDatas;
    //声明适配器
    private MyAdapter myAdapter;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //初始化数据源
        initData();
        //获取控件
        iv_back = findViewById(R.id.iv_back);
        lv = findViewById(R.id.lv);
        et_search = findViewById(R.id.et_search);
        iv_search_btn = findViewById(R.id.iv_search_btn);
        iv_flush = findViewById(R.id.iv_flush);
        //绑定点击监听
        iv_back.setOnClickListener(this);
        iv_search_btn.setOnClickListener(this);
        iv_flush.setOnClickListener(this);

        //ListView相关  创建数据源
//        myDatas = new ArrayList<>();
//        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
//        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
//        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
//        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));
//        myDatas.add(new Food(1, "猪肉", "黄莲", "R.mipmap.pork", "好吃"));

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
            case R.id.iv_search_btn:
                //根据条件搜索想要的数据信息
                seatchData();
                break;
            case R.id.iv_flush:
                flushData();
                break;
        }
    }

    //刷新数据
    private void flushData() {
        //1.获取数据库中查询的全部信息
        List<Food> foodList = dbHelper.getAllFood();
        //清空数据
        myDatas.clear();
        //2.传递给数据源
        for (Food food : foodList) {
            myDatas.add(food);
        }
        //3.将搜索框文本清空
        et_search.setText("");
        //通知适配器更新
        myAdapter.notifyDataSetChanged();

    }

    //搜索信息
    private void seatchData() {
        //1.从编辑框中获取输入的信息，判断是否为空
        if (TextUtils.isEmpty(et_search.getText().toString())) {
            Toast.makeText(this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
            return;//退出方法  不往下执行
        }

        //2. 根据填写的key搜索信息
        List<Food> foodList = dbHelper.getFoodByName(et_search.getText().toString());
        //3. 将这个集合数据传递给数据源
        myDatas.clear();
        for (Food food : foodList) {
            myDatas.add(food);
        }
        //4.更新适配器
        myAdapter.notifyDataSetChanged();
    }

    public void initData() {
        //实例化dbhelper
        dbHelper = DBHelper.getInstance(this);
        //通过dbhelper中查询全部信息getAllFood方法，获取数据源
        List<Food> foodList = dbHelper.getAllFood();
        //将查询结果传递给数据源
        myDatas = new ArrayList<>();
        for (int i = 0; i < foodList.size(); i++) {
            myDatas.add(foodList.get(i));

        }
    }
}