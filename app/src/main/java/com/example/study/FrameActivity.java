package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_click02;
    TextView tv_content01;

    Button btn_num01,btn_num02,btn_num03,btn_num04,btn_change;
    EditText er_result;

    ImageView iv_pic;

    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        btn_click02 = findViewById(R.id.btn_click02);
        tv_content01 = findViewById(R.id.tv_content01);
        btn_num01=findViewById(R.id.btn_num01);
        btn_num02=findViewById(R.id.btn_num02);
        btn_num03=findViewById(R.id.btn_num03);
        btn_num04=findViewById(R.id.btn_num04);
        er_result=findViewById(R.id.et_result);
        btn_change=findViewById(R.id.btn_change);
        iv_pic=findViewById(R.id.iv_pic01);
//        btn_click02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Button btn=(Button) v;
//                System.out.println(btn.getText());
//                System.out.println("点击了按钮2");
//            }
//        });
        btn_click02.setOnClickListener(v->{
            Button btn=(Button) v;
            System.out.println(btn.getText());
            System.out.println("点击了按钮2");
            tv_content01.setText("Hello Harmony");
        });
        btn_num01.setOnClickListener(this);
        btn_num02.setOnClickListener(this);
        btn_num03.setOnClickListener(this);
        btn_num04.setOnClickListener(this);
        btn_change.setOnClickListener(this);
    }

    public void click01(View view) {
        Button btn =(Button)view;
        System.out.println(btn.getText());
        System.out.println("点击了按钮1");
    }

    public void click02(View view) {
    }

    //利用当前的活动页面 全局处理点击监事件
    @Override
    public void onClick(View v) {
        //全局的活动界面中，需要判断V是谁？
        String content;
        switch (v.getId()){
            case R.id.btn_num01:
                //处理点击 1 按钮后操作
                content = btn_num01.getText().toString();
                er_result.setText(content);
                break;
            case R.id.btn_num02:
                content = btn_num02.getText().toString();
                er_result.setText(content);
                break;
            case R.id.btn_num03:
                content = btn_num03.getText().toString();
                er_result.setText(content);
                break;
            case R.id.btn_num04:
                content = btn_num04.getText().toString();
                er_result.setText(content);
                break;
            case R.id.btn_change:
//                System.out.println("qiehuantupian");

                if (!flag) {
                    iv_pic.setImageResource(R.drawable.lvzi);
                    flag=true;
                }else{
                    iv_pic.setImageResource(R.drawable.tiger_back);
                    flag=false;
                }
                break;
        }
    }
}