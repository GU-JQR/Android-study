package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jqr.utils.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 声明按钮控件

    private ImageView iv_foodlist, iv_aboutme;
    private Button btn_addfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建控件
        iv_foodlist = findViewById(R.id.iv_foodlist);
        iv_aboutme = findViewById(R.id.iv_about_me);
        btn_addfood = findViewById(R.id.btn_addfood);

        //按钮绑定点击监听
        iv_foodlist.setOnClickListener(this);
        iv_aboutme.setOnClickListener(this);
        btn_addfood.setOnClickListener(this);

        DBHelper instance = DBHelper.getInstance(this);
        instance.openReadLink();
        instance.openwriteLink();
    }

    @Override
    public void onClick(View v) {
        //判断点击了谁
        switch (v.getId()) {
            case R.id.iv_foodlist:
                //跳转到食品列表界面
                jump(MainActivity.this, FoodActivity.class);
                break;
            case R.id.iv_about_me:
                jump(MainActivity.this, AboutActivity.class);
                break;
            case R.id.btn_addfood:
                // 进入添加界面
                jump(MainActivity.this, AddfFoodActivity.class);
                break;
        }
    }

    //跳转方法
    public void jump(Context packageContext, Class<?> cls) {
        Intent intent = new Intent();
        //显示意图Intent
        intent.setClass(packageContext, cls);
        //执行意图
        startActivity(intent);

    }
}