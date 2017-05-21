package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class ImageDataBaseModel implements Parcelable{
    private int width;
    private int height;
    private String url;

    protected ImageDataBaseModel(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageDataBaseModel> CREATOR = new Creator<ImageDataBaseModel>() {
        @Override
        public ImageDataBaseModel createFromParcel(Parcel in) {
            return new ImageDataBaseModel(in);
        }

        @Override
        public ImageDataBaseModel[] newArray(int size) {
            return new ImageDataBaseModel[size];
        }
    };

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
