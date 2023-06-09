package com.steash.spotifyStats.domains;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Id
    @Column(unique = true)
    private String spotifyId;

    private String name;

    @Column(length = 512)
    private String avatar; // img url

    @OneToMany(mappedBy = "artist", orphanRemoval = true)
    private List<TopArtist> topArtists;

    // private int popularity;
    // private int followers
    // private Genre genres

    // Getters & Setters
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<TopArtist> getTopArtists() {


        return topArtists;
    }

    public void setTopArtists(List<TopArtist> topArtists) {
        this.topArtists = topArtists;
    }
}
