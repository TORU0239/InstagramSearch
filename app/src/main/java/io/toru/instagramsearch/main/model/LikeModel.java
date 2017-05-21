package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class LikeModel implements Parcelable{
    @SerializedName("data")
    private UserModel[] likedUsersData;
    private int count;


    protected LikeModel(Parcel in) {
        likedUsersData = in.createTypedArray(UserModel.CREATOR);
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(likedUsersData, flags);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LikeModel> CREATOR = new Creator<LikeModel>() {
        @Override
        public LikeModel createFromParcel(Parcel in) {
            return new LikeModel(in);
        }

        @Override
        public LikeModel[] newArray(int size) {
            return new LikeModel[size];
        }
    };

    public UserModel[] getLikedUsersData() {
        return likedUsersData;
    }

    public void setLikedUsersData(UserModel[] likedUsersData) {
        this.likedUsersData = likedUsersData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
