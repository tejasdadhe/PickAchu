package com.tejasdadhe.pickachu;

public class MediaData {
    private String mediaId,urlHD, urlThumbnail, userId, userName, userPic, fileFormat, mediaType, date, caption,  mediaDuration;


    public MediaData()
    {
    }

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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
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
}