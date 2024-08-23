package com.example.jqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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

    //创建handler消息通信对象
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                //获取翻页视图控件中的项item 的索引
                int currentItemIndex = vp_about.getCurrentItem();
                vp_about.setCurrentItem(currentItemIndex + 1);
            }
            //发送延迟消息
            sendEmptyMessageDelayed(1, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //初始化数据
        initData();
        handler.sendEmptyMessageDelayed(1, 2000);
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
            //通过布局添加器 将about_item布局添加到 当前viewpager
            View view = LayoutInflater.from(this).inflate(R.layout.about_item, null);
//            通过view视图控件 获取about_item里面的 Imageview对象
            ImageView iv_pager = view.findViewById(R.id.iv_about_item);
            //设置图片的资源位置
            iv_pager.setImageResource(imgIds[i]);
            //将获取的图片对象存放到集合中
            viewList.add(iv_pager);


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
        //创建适配器
        AboutAdapter aboutAdapter = new AboutAdapter(viewList);
        vp_about.setAdapter(aboutAdapter);

        vpPageChangeListener();
    }

    //处理viewPager页面发生改变监听
    public void vpPageChangeListener() {
        vp_about.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滚动过程中 滚动后
//                Log.d("jqr", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                //页面被选中之后
//                Log.d("jqr", "onPageSelected");
                //当翻页到一个新的视图中,圆点处于对应的激活状态
                for (int i = 0; i < viewList.size(); i++) {
                    //全部小圆点灰色
                    pointList.get(i).setImageResource(R.mipmap.a2);
                }
                //将页面对应的小圆点设置成红黑
                pointList.get(position % viewList.size()).setImageResource(R.mipmap.a3);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面滚动状态改变
//                Log.d("jqr", "onPageScrollStateChanged");
            }
        });
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