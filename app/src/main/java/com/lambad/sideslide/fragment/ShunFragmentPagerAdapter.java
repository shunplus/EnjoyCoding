package com.lambad.sideslide.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rxjavademo.R;

import java.util.ArrayList;

/**
 * Created by xushun on  2019/10/24 17:37.
 * Email：shunplus@163.com
 * Des：
 */
public class ShunFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> tab_title_list;//存放标签页标题
    private ArrayList<Integer> tab_icon_list;//存放标签页标题
    private ArrayList<Fragment> fragment_list;//存放ViewPager下的Fragment
    private Context mContext;

    public ShunFragmentPagerAdapter(FragmentManager fm, Context mContext, ArrayList<String> tab_title_list, ArrayList<Integer> tab_icon_list, ArrayList<Fragment> fragment_list) {
        super(fm);
        this.tab_title_list = tab_title_list;
        this.tab_icon_list = tab_icon_list;
        this.fragment_list = fragment_list;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    //注意！！！这里就是我们自定义的布局tab_item
    public View getCustomView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_tab);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab_title);
        //drawable代码在文章最后贴出
        iv.setImageDrawable(mContext.getResources().getDrawable(tab_icon_list.get(position)));
        tv.setText(tab_title_list.get(position));
        return view;
    }

}
