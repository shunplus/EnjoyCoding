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
public class FragmentFour extends BaseLazyLoadFragment {

    private Unbinder unbinder;

    public static FragmentFour newInstance() {

        Bundle args = new Bundle();

        FragmentFour fragment = new FragmentFour();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyLoad() {
        unbinder = ButterKnife.bind(this, getContentView());

    }

    @Override
    public int setContentView() {
        return R.layout.fragment_four;
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
