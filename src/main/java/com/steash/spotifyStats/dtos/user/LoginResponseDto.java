package com.steash.spotifyStats.dtos.user;

public class LoginResponseDto {
    private long userId;

    public LoginResponseDto(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
