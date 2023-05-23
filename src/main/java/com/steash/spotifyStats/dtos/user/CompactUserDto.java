package com.steash.spotifyStats.dtos.user;

public class CompactUserDto {
    private String displayName;

    private String spotifyId;

    private String avatar;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
