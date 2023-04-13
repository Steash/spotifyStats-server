package com.steash.spotifyStats.dtos.topArtist;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.User;

public class SaveTopArtistDto {
    private long id;

//    private long artistId;

    private String artistSpotifyId;

    private int rank;

    private long userId;

    private String accessToken;

    // Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public long getArtistId() {
//        return artistId;
//    }
//
//    public void setArtistId(long artistId) {
//        this.artistId = artistId;
//    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
