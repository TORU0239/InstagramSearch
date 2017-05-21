package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class CommentEachModel implements Parcelable{
    private long id;
    private String text;

    @SerializedName("created_time")
    private long createdTime;

    @SerializedName("from")
    private UserModel commentedUser;

    protected CommentEachModel(Parcel in) {
        id = in.readLong();
        text = in.readString();
        createdTime = in.readLong();
        commentedUser = in.readParcelable(UserModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeLong(createdTime);
        dest.writeParcelable(commentedUser, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommentEachModel> CREATOR = new Creator<CommentEachModel>() {
        @Override
        public CommentEachModel createFromParcel(Parcel in) {
            return new CommentEachModel(in);
        }

        @Override
        public CommentEachModel[] newArray(int size) {
            return new CommentEachModel[size];
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

    public UserModel getCommentedUser() {
        return commentedUser;
    }

    public void setCommentedUser(UserModel commentedUser) {
        this.commentedUser = commentedUser;
    }
}
