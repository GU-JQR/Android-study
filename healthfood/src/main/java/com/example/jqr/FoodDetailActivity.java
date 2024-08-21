package com.example.jqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jqr.entiyt.Food;

import java.io.Serializable;

public class FoodDetailActivity extends AppCompatActivity implements View.OnClickListener {
    //声明定时时长
    private int time = 10;
    //声明控件
    Button btn_back;
    ImageView iv_foodpic;
    TextView tv_tittle, tv_fooddesc, tv_noeatfood, tv_time;

    //传递过来的食品对象
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        //获取从FoodList页面传递的食品信息
        food = (Food) getIntent().getSerializableExtra("food");


        //获取控件
        btn_back = findViewById(R.id.btn_back);
        //设置按钮禁用
        btn_back.setEnabled(false);
        iv_foodpic = findViewById(R.id.iv_foodpic);
        tv_tittle = findViewById(R.id.tv_title);
        tv_fooddesc = findViewById(R.id.tv_desc);
        tv_noeatfood = findViewById(R.id.tv_noeat_food);
        tv_time = findViewById(R.id.tv_time);

        //绑定点击监听
        btn_back.setOnClickListener(this);

        //同步数据显示
        tv_tittle.setText(food.getFoodTitle());
        tv_noeatfood.setText(food.getFoodEat());
        tv_fooddesc.setText(food.getFoodDesc());
        Log.d("jqr", food.getPicPath());


        //将获取图片地址转换成  系统对应的编号(int)
        String picpath = food.getPicPath();  //R.mipmap.
        //字符串截取获取pork
        int dotIndex = picpath.lastIndexOf(".");//1.获取最后小数点位置
        //2.截取最后小数点位置后面的字符
        String picname = picpath.substring(dotIndex + 1);
        //利用Resource 图片的名称映射的上下文标识
        Resources resources = this.getResources();
        int picId = resources.getIdentifier(picname, "mipmap", this.getPackageName());
        //改变图片的资源位置
        iv_foodpic.setImageResource(picId);

        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    //Android里面 主线程 子线程  子线程2  子线程3 ,  消息不可以直接通信  通过Handler对象来进行线程间的通信
    //手动创建一个内部类handle对象
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        //实现handler对象中的处理消息的方法
        @Override
        public void handleMessage(@NonNull Message msg) {
            //判断传递给我们的消息数据  what==1 开始定时
            if (msg.what == 1) {
                //时长倒计时
                time--;
                if (time == 0) {
                    //倒计时结束
                    //倒计时文本清空
                    //返回按钮激活
                    tv_time.setText(" ");
                    //返回按钮激活
                    btn_back.setEnabled(true);
                    btn_back.setBackgroundResource(R.mipmap.btn_back_active);
                } else {
                    //继续倒计时
                    //改变倒计时文本的内容
                    tv_time.setText(time + "s");
                    //继续发送消息 发的消息是1

                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        }
    };
}