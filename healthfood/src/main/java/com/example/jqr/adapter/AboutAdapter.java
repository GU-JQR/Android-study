package com.example.jqr.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class AboutAdapter extends PagerAdapter {
    //数据源
    private List<View> viewList;

    public AboutAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    //获取数据源数量  技巧:integer.MAX_VALUE 无限大  实现循环翻页
    //设置无限大之后 如何获取[0,1,2,3,4]集合里面的对应项 取模方法 postion % viewlist.size();
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    //判断view 和 object是否相等,相等情况下才继续,否则不继续执行
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    //实现初始化和销毁方法
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //container容器  我们的控件存放在容器中 创建 :将控件添加到容器
//        获取控件
        View view = viewList.get(position % viewList.size());
        //判断当前视图首付包含父容器,如果有先移除再添加
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//      获取控件 从容器中移除该控件
        View view = viewList.get(position % viewList.size());
        container.removeView(view);
    }
}
