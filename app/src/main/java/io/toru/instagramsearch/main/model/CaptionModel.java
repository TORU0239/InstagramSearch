package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class CaptionModel implements Parcelable{
    private long id;
    private String text;

    @SerializedName("created_time")
    private long createdTime;

    @SerializedName("from")
    private UserModel authorUser;

    protected CaptionModel(Parcel in) {
        id = in.readLong();
        text = in.readString();
        createdTime = in.readLong();
        authorUser = in.readParcelable(UserModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeLong(createdTime);
        dest.writeParcelable(authorUser, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CaptionModel> CREATOR = new Creator<CaptionModel>() {
        @Override
        public CaptionModel createFromParcel(Parcel in) {
            return new CaptionModel(in);
        }

        @Override
        public CaptionModel[] newArray(int size) {
            return new CaptionModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public UserModel getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(UserModel authorUser) {
        this.authorUser = authorUser;
    }
}
