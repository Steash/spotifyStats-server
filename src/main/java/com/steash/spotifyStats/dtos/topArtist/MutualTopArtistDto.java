package com.steash.spotifyStats.dtos.topArtist;

public class MutualTopArtistDto {
    private String artistName;
    private String artistAvatar;
    private String artistSpotifyId;
    private int topArtistRank;
    private String userSpotifyId;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistAvatar() {
        return artistAvatar;
    }

    public void setArtistAvatar(String artistAvatar) {
        this.artistAvatar = artistAvatar;
    }

    public String getArtistSpotifyId() {
        return artistSpotifyId;
    }

    public void setArtistSpotifyId(String artistSpotifyId) {
        this.artistSpotifyId = artistSpotifyId;
    }

    public int getTopArtistRank() {
        return topArtistRank;
    }

    public void setTopArtistRank(int topArtistRank) {
        this.topArtistRank = topArtistRank;
    }

    public String getUserSpotifyId() {
        return userSpotifyId;
    }

    public void setUserSpotifyId(String userSpotifyId) {
        this.userSpotifyId = userSpotifyId;
    }
}
