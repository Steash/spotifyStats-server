package com.steash.spotifyStats.dtos.topArtist;

public class TopArtistDto {
    private long id;

    private String artistName;

    private String artistSpotifyId;

    private int rank;

    private String userDisplayName;

    private long userId;

    // Getters & Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
