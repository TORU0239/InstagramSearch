package io.toru.instagramsearch.main.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class InstagramItemModel {

    private String id;
    private String code;

    // 다른 모델
    private String user;

    // 다른 모델
    private String images;

    @SerializedName("created_time")
    private long createdTime;

    // caption model
    @SerializedName("caption")
    private String caption;

    @SerializedName("user_has_liked")
    private boolean HasUserLiked;

    // likes model
    @SerializedName("likes")
    private String likes;

    // comments model
    @SerializedName("comments")
    private String comments;

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
}
