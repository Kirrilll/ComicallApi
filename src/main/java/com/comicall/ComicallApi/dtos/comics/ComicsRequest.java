package com.comicall.ComicallApi.dtos.comics;

import java.util.Set;

public class ComicsRequest {
    private String name;
    private String description;
    private int publishYear;
    private String posterPath;
    private String authorName;
    private Set<String> genres;

    public ComicsRequest(){}

    public ComicsRequest(String name, String description, int publishYear, String posterPath, String authorName, Set<String> genres) {
        this.name = name;
        this.description = description;
        this.publishYear = publishYear;
        this.posterPath = posterPath;
        this.authorName = authorName;
        this.genres = genres;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
}
