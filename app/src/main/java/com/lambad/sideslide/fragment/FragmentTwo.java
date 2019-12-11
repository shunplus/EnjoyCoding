package com.lambad.sideslide.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.rxjavademo.R;
import com.lambad.sideslide.item_fragment.ItemFragment;
import com.lambad.sideslide.item_fragment.ItemFragment2;
import com.lambad.sideslide.item_fragment.ItemFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xushun on  2019/10/24 10:36.
 * Email：shunplus@163.com
 * Des：
 */
public class FragmentTwo extends BaseLazyLoadFragment {

    @BindView(R.id.tablayout2)
    XTabLayout tablayout2;
    @BindView(R.id.viewpager2)
    ViewPager viewpager2;
    private Unbinder unbinder;
    private List<Fragment> mFragments;
    private ItemFragmentAdapter imAdapter;
    private String[] names = {"我关注的人", "关注我的人"};

    public static FragmentTwo newInstance() {

        Bundle args = new Bundle();

        FragmentTwo fragment = new FragmentTwo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyLoad() {
        unbinder = ButterKnife.bind(this, getContentView());
        init();

    }

    @Override
    public int setContentView() {
        return R.layout.fragment_two;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void init() {

        mFragments = new ArrayList<>();
        mFragments.add(new ItemFragment());
        mFragments.add(new ItemFragment2());
        imAdapter = new ItemFragmentAdapter(getChildFragmentManager(), names, mFragments, getContext());
        viewpager2.setAdapter(imAdapter);
        tablayout2.setupWithViewPager(viewpager2);


    }
}
