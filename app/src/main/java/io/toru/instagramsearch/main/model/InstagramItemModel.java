package io.toru.instagramsearch.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class InstagramItemModel implements Parcelable{

    private String id;
    private String code;

    private UserModel user;

    private ImageModel images;

    @SerializedName("created_time")
    private long createdTime;

    @SerializedName("caption")
    private CaptionModel caption;

    @SerializedName("user_has_liked")
    private boolean HasUserLiked;

    @SerializedName("likes")
    private LikeModel likes;

    @SerializedName("comments")
    private CommentModel comments;

    @SerializedName("can_view_comments")
    private boolean canViewComments;

    @SerializedName("can_delete_comments")
    private boolean canDeleteComments;

    private String type;
    private String link;

    private LocationModel location;

    @SerializedName("alt_media_url")
    private String altMediaUrl;

    // carousel model
    @SerializedName("carousel_media")
    private CarouselModel[] carouselMedia;

    // video model
    @SerializedName("videos")
    private VideoModel video;

    @SerializedName("video_views")
    private int videoViewsCount;

    protected InstagramItemModel(Parcel in) {
        id = in.readString();
        code = in.readString();
        user = in.readParcelable(UserModel.class.getClassLoader());
        images = in.readParcelable(ImageModel.class.getClassLoader());
        createdTime = in.readLong();
        caption = in.readParcelable(CaptionModel.class.getClassLoader());
        HasUserLiked = in.readByte() != 0;
        likes = in.readParcelable(LikeModel.class.getClassLoader());
        canViewComments = in.readByte() != 0;
        canDeleteComments = in.readByte() != 0;
        type = in.readString();
        link = in.readString();
        location = in.readParcelable(LocationModel.class.getClassLoader());
        altMediaUrl = in.readString();
        carouselMedia = in.createTypedArray(CarouselModel.CREATOR);
        video = in.readParcelable(VideoModel.class.getClassLoader());
        videoViewsCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(code);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(images, flags);
        dest.writeLong(createdTime);
        dest.writeParcelable(caption, flags);
        dest.writeByte((byte) (HasUserLiked ? 1 : 0));
        dest.writeParcelable(likes, flags);
        dest.writeByte((byte) (canViewComments ? 1 : 0));
        dest.writeByte((byte) (canDeleteComments ? 1 : 0));
        dest.writeString(type);
        dest.writeString(link);
        dest.writeParcelable(location, flags);
        dest.writeString(altMediaUrl);
        dest.writeTypedArray(carouselMedia, flags);
        dest.writeParcelable(video, flags);
        dest.writeInt(videoViewsCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InstagramItemModel> CREATOR = new Creator<InstagramItemModel>() {
        @Override
        public InstagramItemModel createFromParcel(Parcel in) {
            return new InstagramItemModel(in);
        }

        @Override
        public InstagramItemModel[] newArray(int size) {
            return new InstagramItemModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ImageModel getImages() {
        return images;
    }

    public void setImages(ImageModel images) {
        this.images = images;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public CaptionModel getCaption() {
        return caption;
    }

    public void setCaption(CaptionModel caption) {
        this.caption = caption;
    }

    public boolean isHasUserLiked() {
        return HasUserLiked;
    }

    public void setHasUserLiked(boolean hasUserLiked) {
        HasUserLiked = hasUserLiked;
    }

    public LikeModel getLikes() {
        return likes;
    }

    public void setLikes(LikeModel likes) {
        this.likes = likes;
    }

    public CommentModel getComments() {
        return comments;
    }

    public void setComments(CommentModel comments) {
        this.comments = comments;
    }

    public boolean isCanViewComments() {
        return canViewComments;
    }

    public void setCanViewComments(boolean canViewComments) {
        this.canViewComments = canViewComments;
    }

    public boolean isCanDeleteComments() {
        return canDeleteComments;
    }

    public void setCanDeleteComments(boolean canDeleteComments) {
        this.canDeleteComments = canDeleteComments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getAltMediaUrl() {
        return altMediaUrl;
    }

    public void setAltMediaUrl(String altMediaUrl) {
        this.altMediaUrl = altMediaUrl;
    }

    public CarouselModel[] getCarouselMedia() {
        return carouselMedia;
    }

    public void setCarouselMedia(CarouselModel[] carouselMedia) {
        this.carouselMedia = carouselMedia;
    }

    public VideoModel getVideo() {
        return video;
    }

    public void setVideo(VideoModel video) {
        this.video = video;
    }

    public int getVideoViewsCount() {
        return videoViewsCount;
    }

    public void setVideoViewsCount(int videoViewsCount) {
        this.videoViewsCount = videoViewsCount;
    }
}
