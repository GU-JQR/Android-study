package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jqr.entiyt.Food;
import com.example.jqr.utils.CommonUtils;
import com.example.jqr.utils.DBHelper;

public class AddfFoodActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    private ImageView iv_back;

    private Button btn_add;
    private EditText et_foodname, et_foodnoeat, et_foodpic, et_foodesc;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addf_food);
        //创建控件
        initData();
    }

    //处理全局点击事件
    private void initData() {
        //获取控件
        iv_back = findViewById(R.id.iv_back);
        btn_add = findViewById(R.id.btn_addfood);
        et_foodname = findViewById(R.id.et_foodname);
        et_foodpic = findViewById(R.id.et_picpath);
        et_foodesc = findViewById(R.id.et_fooddesc);
        et_foodnoeat = findViewById(R.id.et_noeat);
        //点击监听绑定
        iv_back.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                //返回 出栈,上一个界面显示
                finish();
                break;
            case R.id.btn_addfood:
                //点击添加按钮
                //1.判断必填项是否为空
                if (TextUtils.isEmpty(et_foodname.getText())) {
//                    Toast.makeText(this, "食品名称不能为空", Toast.LENGTH_SHORT).show();
                    CommonUtils.MyToast(this, "食品名称不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(et_foodnoeat.getText())) {
                    CommonUtils.MyToast(this, "不宜同吃不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(et_foodpic.getText())) {
                    CommonUtils.MyToast(this, "图片资源不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(et_foodesc.getText())) {
                    CommonUtils.MyToast(this, "食品描述不能为空!");
                    return;
                }

                //2.将数据封装成Food对象
                Food food = new Food(et_foodname.getText().toString(), et_foodnoeat.getText().toString(), et_foodpic.getText().toString(), et_foodesc.getText().toString());
                //3.通过DBhelper对象 添加食物 的方法调用 局部变量转成全局变量  ctrl+alt+f
                dbHelper = DBHelper.getInstance(this);
                dbHelper.addFood(food, this);

                break;
        }
    }
}