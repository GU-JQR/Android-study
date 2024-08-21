package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FoodDetailActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        //获取控件
        btn_back = findViewById(R.id.btn_back);
        //绑定点击监听
        btn_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}