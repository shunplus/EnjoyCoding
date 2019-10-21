package com.example.rxjavademo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by xushun on  2019/10/20 11:49.
 * Email：shunplus@163.com
 * Des：
 */
public class StubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_layout);
    }
}
