package com.steash.spotifyStats.dtos.topArtist;

public class SaveTopArtistDto {
    private long id;

    private String artistSpotifyId;

    private String artistName;

    private int rank;

    private String avatar;

    private String userSpotifyId;

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtistSpotifyId() {
        return artistSpotifyId;
    }

    public void setArtistSpotifyId(String artistSpotifyId) {
        this.artistSpotifyId = artistSpotifyId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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

    public String getUserSpotifyId() {
        return userSpotifyId;
    }

    public void setUserSpotifyId(String userSpotifyId) {
        this.userSpotifyId = userSpotifyId;
    }
}
