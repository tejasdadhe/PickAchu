package com.tejasdadhe.pickachu;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.IntDef;

public class MediaData implements Parcelable {



    private String mediaId,urlHD, urlThumbnail, userId, userName, userPic, fileFormat, date, caption,  mediaDuration;

    @MediaType
    private int mediaType;

    @IntDef({MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO,MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE,MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO})
    @interface MediaType {}

    MediaData() {}

    public String getUrlHD() {
        return urlHD;
    }

    public void setUrlHD(String urlHD) {
        this.urlHD = urlHD;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @MediaType
    public int getMediaType() { return mediaType; }

    public void setMediaType(@MediaType int mediaType) {
        this.mediaType = mediaType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setMediaDuration(String mediaDuration) {
        this.mediaDuration = mediaDuration;
    }

    public String getMediaDuration() {
        return mediaDuration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mediaId);
        dest.writeString(this.urlHD);
        dest.writeString(this.urlThumbnail);
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userPic);
        dest.writeString(this.fileFormat);
        dest.writeString(this.date);
        dest.writeString(this.caption);
        dest.writeString(this.mediaDuration);
        dest.writeInt(this.mediaType);
    }

    protected MediaData(Parcel in) {
        this.mediaId = in.readString();
        this.urlHD = in.readString();
        this.urlThumbnail = in.readString();
        this.userId = in.readString();
        this.userName = in.readString();
        this.userPic = in.readString();
        this.fileFormat = in.readString();
        this.date = in.readString();
        this.caption = in.readString();
        this.mediaDuration = in.readString();
        this.mediaType = in.readInt();
    }

    public static final Parcelable.Creator<MediaData> CREATOR = new Parcelable.Creator<MediaData>() {
        @Override
        public MediaData createFromParcel(Parcel source) {
            return new MediaData(source);
        }

        @Override
        public MediaData[] newArray(int size) {
            return new MediaData[size];
        }
    };
}