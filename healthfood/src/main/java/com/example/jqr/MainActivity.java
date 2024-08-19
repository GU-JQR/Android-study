package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 声明按钮控件

    ImageView iv_foodlist, iv_aboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建控件
        iv_foodlist = findViewById(R.id.iv_foodlist);
        iv_aboutme = findViewById(R.id.iv_about_me);

        //按钮绑定点击监听
        iv_foodlist.setOnClickListener(this);
        iv_aboutme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //判断点击了谁
        switch (v.getId()) {
            case R.id.iv_foodlist:
                //跳转到食品列表界面
                //显示意图Intent
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FoodActivity.class);
//                执行意图
                startActivity(intent);

                break;
            case R.id.iv_about_me:

                break;
        }
    }
}