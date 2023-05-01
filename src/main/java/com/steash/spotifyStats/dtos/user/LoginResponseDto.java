package com.steash.spotifyStats.dtos.user;

public class LoginResponseDto {
    private String userSpotifyId;

    public LoginResponseDto(String userSpotifyId) {
        this.userSpotifyId = userSpotifyId;
    }

    public String getUserSpotifyId() {
        return userSpotifyId;
    }

    public void setUserSpotifyId(String userSpotifyId) {
        this.userSpotifyId = userSpotifyId;
    }
}
