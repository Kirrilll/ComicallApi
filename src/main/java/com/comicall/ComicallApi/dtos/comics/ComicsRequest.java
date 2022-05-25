package com.comicall.ComicallApi.dtos.comics;

import com.comicall.ComicallApi.dtos.page.PageRequest;

import java.util.Set;

public class ComicsRequest {
    private String name;
    private String description;
    private int publishYear;
    private String posterPath;
    private Set<String> genres;
    private Set<PageRequest> pages;

    public ComicsRequest(){}

    public ComicsRequest(String name, String description, int publishYear, String posterPath, Set<String> genres, Set<PageRequest> pages) {
        this.name = name;
        this.description = description;
        this.publishYear = publishYear;
        this.posterPath = posterPath;
        this.genres = genres;
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Set<PageRequest> getPages() {
        return pages;
    }

    public void setPages(Set<PageRequest> pages) {
        this.pages = pages;
    }
}
