package com.lambad.sideslide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLazyLoadFragment extends Fragment {

    View view;
    private boolean isFirstLoad = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        //让子类实现初始化视图
        initEvent();//初始化事件

        isFirstLoad = true;//视图创建完成，将变量置为true

        if (getUserVisibleHint()) {//如果Fragment可见进行数据加载
            onLazyLoad();
            isFirstLoad = false;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = false;//视图销毁将变量置为false
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstLoad && isVisibleToUser) {//视图变为可见并且是第一次加载
            onLazyLoad();
            isFirstLoad = false;
        }

    }

    /**
     * 获取设置的布局
     *
     * @return
     */
    public View getContentView() {
        return view;
    }

    //数据加载接口，留给子类实现
    public abstract void onLazyLoad();

    //初始化视图接口，子类必须实现
    public abstract int setContentView();

    //初始化事件接口，留给子类实现
    public abstract void initEvent();

}
