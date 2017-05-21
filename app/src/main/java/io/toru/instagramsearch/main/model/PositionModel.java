package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class PositionModel implements Parcelable{
    @SerializedName("x")
    private double locationX;

    @SerializedName("y")
    private double locationY;


    protected PositionModel(Parcel in) {
        locationX = in.readDouble();
        locationY = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(locationX);
        dest.writeDouble(locationY);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PositionModel> CREATOR = new Creator<PositionModel>() {
        @Override
        public PositionModel createFromParcel(Parcel in) {
            return new PositionModel(in);
        }

        @Override
        public PositionModel[] newArray(int size) {
            return new PositionModel[size];
        }
    };

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }
}
