package com.steash.spotifyStats.dtos.topArtist;

import jakarta.persistence.Column;

public class UserTopArtistDto {


    private String artistName;

    private String artistSpotifyId;

    private int rank;

    private String avatar; // img url

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistSpotifyId() {
        return artistSpotifyId;
    }

    public void setArtistSpotifyId(String artistSpotifyId) {
        this.artistSpotifyId = artistSpotifyId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
