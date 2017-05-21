package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class UsersInPhotoModel implements Parcelable{
    private UserModel user;
    private PositionModel position;

    protected UsersInPhotoModel(Parcel in) {
        user = in.readParcelable(UserModel.class.getClassLoader());
        position = in.readParcelable(PositionModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeParcelable(position, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UsersInPhotoModel> CREATOR = new Creator<UsersInPhotoModel>() {
        @Override
        public UsersInPhotoModel createFromParcel(Parcel in) {
            return new UsersInPhotoModel(in);
        }

        @Override
        public UsersInPhotoModel[] newArray(int size) {
            return new UsersInPhotoModel[size];
        }
    };

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }
}
