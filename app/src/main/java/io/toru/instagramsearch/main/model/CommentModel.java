package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public class CommentModel implements Parcelable{

    private CommentEachModel[] data;
    private int count;

    protected CommentModel(Parcel in) {
        data = in.createTypedArray(CommentEachModel.CREATOR);
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(data, flags);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommentModel> CREATOR = new Creator<CommentModel>() {
        @Override
        public CommentModel createFromParcel(Parcel in) {
            return new CommentModel(in);
        }

        @Override
        public CommentModel[] newArray(int size) {
            return new CommentModel[size];
        }
    };

    public CommentEachModel[] getData() {
        return data;
    }

    public void setData(CommentEachModel[] data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
