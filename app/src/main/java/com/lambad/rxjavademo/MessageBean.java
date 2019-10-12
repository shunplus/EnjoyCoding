package com.lambad.rxjavademo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xushun on  2019/10/11 18:00.
 * Email：shunplus@163.com
 * Des：
 */
public class MessageBean implements Parcelable {
    private String age;
    private int page;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.age);
        dest.writeInt(this.page);
    }

    public MessageBean() {
    }

    protected MessageBean(Parcel in) {
        this.age = in.readString();
        this.page = in.readInt();
    }

    public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel source) {
            return new MessageBean(source);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };
}
