package com.zxy.androiddemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xiyingzhu
 * @date 2019/3/4
 */
public class DragBean implements Parcelable {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public DragBean() {
    }

    protected DragBean(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Creator<DragBean> CREATOR = new Creator<DragBean>() {
        @Override
        public DragBean createFromParcel(Parcel source) {
            return new DragBean(source);
        }

        @Override
        public DragBean[] newArray(int size) {
            return new DragBean[size];
        }
    };
}
