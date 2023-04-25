package com.steash.spotifyStats.domains;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NameConstraint
    private String displayName;

    @Column(unique = true)
    private String spotifyId;

    @Column(unique = true)
    private String email;

    private String country;

    @Column(length = 512)
    private String avatar; // img url

    private String product; // e.g. premium | standard
    private String accessToken;

    private String refreshToken;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<TopArtist> topArtists;

    // Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
//        if (this.testEnum == TestEnum.PREMIUM) {
//
//        }
//        this.testEnum2.PREMIUM => "PREMIUM"
//        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<TopArtist> getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(List<TopArtist> topArtists) {
        this.topArtists = topArtists;
    }
}
