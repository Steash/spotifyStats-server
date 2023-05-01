package com.steash.spotifyStats.dtos.topArtist;

public class TopArtistUserDto {
    private String userSpotifyId;
    private String displayName;
    private String avatar;

    public String getUserSpotifyId() {
        return userSpotifyId;
    }

    public void setUserSpotifyId(String userSpotifyId) {
        this.userSpotifyId = userSpotifyId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
