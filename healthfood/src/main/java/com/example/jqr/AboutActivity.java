package com.example.jqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jqr.adapter.AboutAdapter;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    //声明控件
    private TextView tv_share;

    private ViewPager vp_about;
    private LinearLayout ll_point;

    private int[] imgIds = {R.mipmap.ab1, R.mipmap.ab2, R.mipmap.ab3, R.mipmap.ab4, R.mipmap.ab5};
    private List<View> viewList;
    private List<ImageView> pointList;

    private AboutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //初始化数据
        initData();
    }

    private void initData() {
        //获取控件
        tv_share = findViewById(R.id.tv_share);
        vp_about = findViewById(R.id.vp_about);
        ll_point = findViewById(R.id.ll_point);
        //绑定点击事件
        tv_share.setOnClickListener(this);
        //集合实例化
        viewList = new ArrayList<>();
        pointList = new ArrayList<>();

        //创建小圆点对象  存放到pointList中,添加到ll_point容器里面
        // 有多少张图片创建多少个小圆点
        for (int i = 0; i < imgIds.length; i++) {
            //1.添加翻页view到viewpage中

            //2.创建小圆点(通过逻辑代码创建),添加到linelayout容器中
            //先创建小圆点容器参数对象
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 20, 0);
            //创建Image对象  小圆点本身
            ImageView iv_point = new ImageView(this);
            iv_point.setLayoutParams(lp);
            //设置小圆点图片外边距
            iv_point.setImageResource(R.mipmap.a2);
            //添加到
            ll_point.addView(iv_point);
            pointList.add(iv_point);
        }
        //默认激活第一个小圆点
//        ll_point.addView(ll_point);
        pointList.get(0).setImageResource(R.mipmap.a3);
    }

    //处理点击逻辑
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share:
                //通过隐式意图,打开分享功能
                //拨号
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                Uri uri = Uri.parse("tel:1388888888");
//                intent.setData(uri);
//                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String msg = "健康饮食非常重要,了解饮食各方面营养和热量,让你变得更健康--";
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(intent.createChooser(intent, "健康饮食分享"));
                break;
        }
    }
}