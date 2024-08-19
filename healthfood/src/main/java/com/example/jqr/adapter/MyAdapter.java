package com.example.jqr.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jqr.R;
import com.example.jqr.entiyt.Food;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Food> myDatas;//数据源
    private Context context;//上下文

    //通过构造函数传递上面两个参数
    public MyAdapter(List<Food> myDatas, Context context) {
        this.myDatas = myDatas;
        this.context = context;
    }

    //获取集合中总数据
    @Override
    public int getCount() {
        return myDatas.size();
    }

    //根据索引获取对应item
    @Override
    public Object getItem(int position) {
        return myDatas.get(position);
    }

    //获取item的索引
    @Override
    public long getItemId(int position) {
        return position;
    }

    //创建item并渲染， 迭代数据源中food  进行操作
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明ViewHolder
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
            //创建的view传递到ViewHolder
            viewHolder = new ViewHolder(convertView);
            //添加到view标记中Tag中
            convertView.setTag(viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //渲染数据 get(i)->一个食物 get(i).getFoodTitle()当前食物的标题
//        myDatas.get(position).getFoodTitle() 赋值给TextView为标题的控件
        viewHolder.tv_title.setText(myDatas.get(position).getFoodTitle());
        viewHolder.tv_noeat.setText(myDatas.get(position).getFoodEat());
        //假设你获取的图片路径是字符串 “R.mipmap.pork” myDatas.get(position).getPicPath();
        String picpath = myDatas.get(position).getPicPath();
        //获取图片路径末尾名称  录入 pork
        int lastDot = picpath.lastIndexOf(".");
        String picName = picpath.substring(lastDot + 1);
        //获取资源处理信息
        Resources resources = context.getResources();
        //解析图片名称 获取在上下文中对应的图片ID值
        int picId = resources.getIdentifier(picName, "mipmap", context.getPackageName());

        viewHolder.iv_foodpic.setImageResource(picId);

        return convertView;//返回渲染完成的view
    }

    final class ViewHolder {
        //属性：依据是food——item中可变的的控件
        private TextView tv_title, tv_noeat;
        private ImageView iv_foodpic;

        public ViewHolder(View v) {
            this.tv_title = v.findViewById(R.id.tv_title);
            this.tv_noeat = v.findViewById(R.id.tv_noeat);
            this.iv_foodpic = v.findViewById(R.id.iv_foodpic);

        }
    }
}
