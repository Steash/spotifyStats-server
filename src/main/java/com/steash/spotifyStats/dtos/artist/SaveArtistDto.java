package com.steash.spotifyStats.dtos.artist;

import jakarta.persistence.Column;

public class SaveArtistDto {
    private String spotifyId;
    private String name;

    private String avatar;

    public String getSpotifyId() {
    return spotifyId;
}

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
