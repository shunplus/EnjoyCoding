package com.lambad.sideslide;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.rxjavademo.R;
import com.lambad.sideslide.fragment.FragmentFour;
import com.lambad.sideslide.fragment.FragmentOne;
import com.lambad.sideslide.fragment.FragmentThree;
import com.lambad.sideslide.fragment.FragmentTwo;
import com.lambad.sideslide.fragment.ShunFragmentPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xushun on  2019/10/22 10:03.
 * Email：shunplus@163.com
 * Des：
 */
public class SideSlideActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    private ArrayList<String> tab_title_list = new ArrayList<>();//存放标签页标题
    private ArrayList<Integer> tab_icon_list = new ArrayList<>();//存放标签页icon
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment
    private ShunFragmentPagerAdapter adapter;
    private Unbinder bind;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_slide_layout);
        bind = ButterKnife.bind(this);
        mContext = this;
        initView();
    }


    /**
     * des:
     *
     * @author xushun
     * @time 2019/10/24 17:54
     */
    public void initView() {
        tab_title_list.add("首页");
        tab_title_list.add("案件");
        tab_title_list.add("智库");
        tab_title_list.add("工具");
        fragment_list.add(FragmentOne.newInstance());
        fragment_list.add(FragmentTwo.newInstance());
        fragment_list.add(FragmentThree.newInstance());
        fragment_list.add(FragmentFour.newInstance());
        tab_icon_list.add(R.drawable.icon_nava1);
        tab_icon_list.add(R.drawable.icon_nava2);
        tab_icon_list.add(R.drawable.icon_nava3);
        tab_icon_list.add(R.drawable.icon_nava4);
        adapter = new ShunFragmentPagerAdapter(getSupportFragmentManager(), this, tab_title_list, tab_icon_list, fragment_list);
        viewPager.setAdapter(adapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout与Viewpager联动起来
        tabLayout.setTabsFromPagerAdapter(adapter);//给TabLayout设置适配器
        for (int i = 0; i < 4; i++) {
            XTabLayout.Tab tab = tabLayout.getTabAt(i);
            //注意！！！这里就是添加我们自定义的布局
            tab.setCustomView(adapter.getCustomView(i));
            //这里是初始化时，默认item0被选中，setSelected（true）是为了给图片和文字设置选中效果，代码在文章最后贴出
            if (i == 0) {
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_tab)).setSelected(true);
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab_title)).setSelected(true);
            }
        }
        tabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                //选中了tab的逻辑
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_tab)).setSelected(true);
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab_title)).setSelected(true);
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
                //未选中tab的逻辑
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_tab)).setSelected(false);
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab_title)).setSelected(false);
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
                //再次选中tab的逻辑
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }
}
