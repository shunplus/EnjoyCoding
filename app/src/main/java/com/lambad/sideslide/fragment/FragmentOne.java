package com.lambad.sideslide.fragment;

import android.os.Bundle;

import com.example.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xushun on  2019/10/24 10:36.
 * Email：shunplus@163.com
 * Des：
 */
public class FragmentOne extends BaseLazyLoadFragment {

    private Unbinder unbinder;

    public static FragmentOne newInstance() {

        Bundle args = new Bundle();

        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyLoad() {
        unbinder = ButterKnife.bind(this, getContentView());

    }

    @Override
    public int setContentView() {
        return R.layout.fragment_one;
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
}
