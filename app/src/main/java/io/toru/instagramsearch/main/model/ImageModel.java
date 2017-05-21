package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class ImageModel implements Parcelable{
    @SerializedName("thumbnail")
    private ImageDataBaseModel thumbnailImage;

    @SerializedName("low_resolution")
    private ImageDataBaseModel lowResolution;

    @SerializedName("standard_resolution")
    private ImageDataBaseModel standardResolution;

    protected ImageModel(Parcel in) {
        thumbnailImage = in.readParcelable(ImageDataBaseModel.class.getClassLoader());
        lowResolution = in.readParcelable(ImageDataBaseModel.class.getClassLoader());
        standardResolution = in.readParcelable(ImageDataBaseModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(thumbnailImage, flags);
        dest.writeParcelable(lowResolution, flags);
        dest.writeParcelable(standardResolution, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public ImageDataBaseModel getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(ImageDataBaseModel thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public ImageDataBaseModel getLowResolution() {
        return lowResolution;
    }

    public void setLowResolution(ImageDataBaseModel lowResolution) {
        this.lowResolution = lowResolution;
    }

    public ImageDataBaseModel getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(ImageDataBaseModel standardResolution) {
        this.standardResolution = standardResolution;
    }
}
