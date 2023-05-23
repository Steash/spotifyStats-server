package com.steash.spotifyStats.dtos.user;

import com.steash.spotifyStats.dtos.topArtist.UserTopArtistDto;

import java.util.List;

public class MusicBuddyDto {

    private String displayName;
    private String spotifyId;
    private String avatar;

    private List<UserTopArtistDto> mutualTopArtists;

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

    public List<UserTopArtistDto> getMutualTopArtists() {
        return mutualTopArtists;
    }

    public void setMutualTopArtists(List<UserTopArtistDto> mutualTopArtists) {
        this.mutualTopArtists = mutualTopArtists;
    }
}


