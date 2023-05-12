package com.steash.spotifyStats.dtos.user;

import java.util.List;

public class SearchRspDto<O extends Object> {
    private int page;
    private int totalPages;
    private int elementCount;
    private List<O> content;

    public SearchRspDto() {

    }

    public SearchRspDto(int page, int totalPages, int elementCount, List<O> content) {
        this.page = page;
        this.totalPages = totalPages;
        this.elementCount = elementCount;
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    public List<O> getContent() {
        return content;
    }

    public void setContent(List<O> content) {
        this.content = content;
    }

    //    private String displayName;
//
//    private String spotifyId;
//
//    private String email;
//
//    private String avatar;
//
//    public String getDisplayName() {
//        return displayName;
//    }
//
//    public void setDisplayName(String displayName) {
//        this.displayName = displayName;
//    }
//
//    public String getSpotifyId() {
//        return spotifyId;
//    }
//
//    public void setSpotifyId(String spotifyId) {
//        this.spotifyId = spotifyId;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
}
