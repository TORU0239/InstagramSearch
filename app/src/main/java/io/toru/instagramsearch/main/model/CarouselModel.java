package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class CarouselModel implements Parcelable{

    private String type;
    private ImageModel images;

    @SerializedName("users_in_photo")
    private UsersInPhotoModel[] usersInPhoto;


    protected CarouselModel(Parcel in) {
        type = in.readString();
        images = in.readParcelable(ImageModel.class.getClassLoader());
        usersInPhoto = in.createTypedArray(UsersInPhotoModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeParcelable(images, flags);
        dest.writeTypedArray(usersInPhoto, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarouselModel> CREATOR = new Creator<CarouselModel>() {
        @Override
        public CarouselModel createFromParcel(Parcel in) {
            return new CarouselModel(in);
        }

        @Override
        public CarouselModel[] newArray(int size) {
            return new CarouselModel[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageModel getImages() {
        return images;
    }

    public void setImages(ImageModel images) {
        this.images = images;
    }

    public UsersInPhotoModel[] getUsersInPhoto() {
        return usersInPhoto;
    }

    public void setUsersInPhoto(UsersInPhotoModel[] usersInPhoto) {
        this.usersInPhoto = usersInPhoto;
    }
}
