package com.example.rxjavademo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xushun on  2019/10/11 18:13.
 * Email：shunplus@163.com
 * Des：
 */
public class Bean implements Parcelable {
    public static final Creator<Bean> CREATOR = new Creator<Bean>() {
        @Override
        public Bean createFromParcel(Parcel source) {
            return new Bean(source);
        }

        @Override
        public Bean[] newArray(int size) {
            return new Bean[size];
        }
    };
    private int id;

    public Bean() {
    }

    protected Bean(Parcel in) {
        this.id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
