package com.steash.spotifyStats.dtos.topArtist;

import com.steash.spotifyStats.domains.Artist;
import com.steash.spotifyStats.domains.User;

public class TopArtistDto {
    private long id;

    private Artist artist;

    private int rank;

    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
