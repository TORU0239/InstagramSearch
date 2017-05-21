package io.toru.instagramsearch.main.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class InstagramItemModel {

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
    // Location 모델
    private String location;

    @SerializedName("alt_media_url")
    private String altMediaUrl;

    // carousel model
    @SerializedName("carousel_media")
    private String carouselMedial;

}
