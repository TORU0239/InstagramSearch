package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class VideoModel implements Parcelable{
    @SerializedName("standard_resolution")
    private ImageDataBaseModel standardResolutionModel;

    @SerializedName("low_bandwidth")
    private ImageDataBaseModel lowBandWidthModel;

    @SerializedName("low_resolution")
    private ImageDataBaseModel lowResolutionModel;

    protected VideoModel(Parcel in) {
        standardResolutionModel = in.readParcelable(ImageDataBaseModel.class.getClassLoader());
        lowBandWidthModel = in.readParcelable(ImageDataBaseModel.class.getClassLoader());
        lowResolutionModel = in.readParcelable(ImageDataBaseModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(standardResolutionModel, flags);
        dest.writeParcelable(lowBandWidthModel, flags);
        dest.writeParcelable(lowResolutionModel, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel in) {
            return new VideoModel(in);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };

    public ImageDataBaseModel getStandardResolutionModel() {
        return standardResolutionModel;
    }

    public void setStandardResolutionModel(ImageDataBaseModel standardResolutionModel) {
        this.standardResolutionModel = standardResolutionModel;
    }

    public ImageDataBaseModel getLowBandWidthModel() {
        return lowBandWidthModel;
    }

    public void setLowBandWidthModel(ImageDataBaseModel lowBandWidthModel) {
        this.lowBandWidthModel = lowBandWidthModel;
    }

    public ImageDataBaseModel getLowResolutionModel() {
        return lowResolutionModel;
    }

    public void setLowResolutionModel(ImageDataBaseModel lowResolutionModel) {
        this.lowResolutionModel = lowResolutionModel;
    }
}
