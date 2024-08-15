package com.example.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.study.R;
import com.example.study.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器 做ListView高级控件的数据渲染操作
 * 属性 数据源  上下文  （通过活动界面传递过来进行处理）
 * 继承 BaseAdapter 站在巨人的肩膀上
 */
public class MyAdapter extends BaseAdapter {

    //    数据源
    private List<Person> datas = new ArrayList<>();
    //    与活动界面能够关联的上下文
    private Context context;

    //构造方法初始化数据 （初始化的 对应活动界面传递的上下文和数据）
    public MyAdapter(List<Person> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    //能够获取集合中项目的总数量
    @Override
    public int getCount() {
        return datas.size();
    }

    //根据索引获取对应item项
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    //根据索引 获取对应item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //创建view对象控件（以list_item为模版），并且使用数据源进行渲染
    //1.getview 根据数据源遍历，每一个项目都会执行一遍getview
    //2.converView 是翻滚视图被覆盖的
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("postion:" + position);
        //声明视图提供者对象
        ViewHolder viewHolder;
        //判断converView是否为空
        if (convertView == null) {
            System.out.println("converView:" + position);
            //布局填充器，创建一个新的view
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            //把创建好的converview传递给viewholder
            viewHolder = new ViewHolder(convertView);
            //设置tag标记
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //通过viewHolder进行数据渲染
        viewHolder.tv_name.setText(datas.get(position).getName());
        viewHolder.tv_age.setText(datas.get(position).getAge().toString());
        return convertView;
    }

    //内部类  视图提供者，存放item布局里面的控件对象
    final class ViewHolder {
        //属性 对应List_item里面数据更改的控件
        private TextView tv_name, tv_age;

        //构造方法 此处的view 是上面getview方法根据List_item模版创建的视图对象
        public ViewHolder(View v) {
            tv_name = v.findViewById(R.id.item_name);
            tv_age = v.findViewById(R.id.item_age);
        }
    }
}
