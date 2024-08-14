package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_user,et_pass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user=findViewById(R.id.et_user);
        et_pass=findViewById(R.id.et_pass);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:

                if(TextUtils.isEmpty(et_user.getText())){
                    Toast.makeText(this,"用户名不能为空",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(et_pass.getText())){
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
                }
                if(et_user.getText().toString().equals("tom")&&et_pass.getText().toString().equals("123456")){
                    Intent intent = new Intent();
                    intent.setClass(this,FrameActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}