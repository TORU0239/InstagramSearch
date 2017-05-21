package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class InstagramModel implements Parcelable {

    @SerializedName("items")
    private InstagramItemModel[] itemList;

    @SerializedName("more_available")
    private boolean isMoreAvailable;

    @SerializedName("status")
    private String status;

    protected InstagramModel(Parcel in) {
        isMoreAvailable = in.readByte() != 0;
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isMoreAvailable ? 1 : 0));
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InstagramModel> CREATOR = new Creator<InstagramModel>() {
        @Override
        public InstagramModel createFromParcel(Parcel in) {
            return new InstagramModel(in);
        }

        @Override
        public InstagramModel[] newArray(int size) {
            return new InstagramModel[size];
        }
    };

    public InstagramItemModel[] getItemList() {
        return itemList;
    }

    public void setItemList(InstagramItemModel[] itemList) {
        this.itemList = itemList;
    }

    public boolean isMoreAvailable() {
        return isMoreAvailable;
    }

    public void setMoreAvailable(boolean moreAvailable) {
        isMoreAvailable = moreAvailable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
