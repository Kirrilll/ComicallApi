package com.comicall.ComicallApi.dtos.comics;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;

import java.util.Collection;

public class ComicsResponse {
    private Long id;
    private String name;
    private String authorName;
    private String posterPath;
    private int publishYear;
    private String description;
    private Collection<GenreDTO> genres;
    private boolean isReady;

    public ComicsResponse(){}

    public ComicsResponse(Long id, String name, String authorName, String posterPath, int publishYear, String description, Collection<GenreDTO> genres, boolean isReady) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.posterPath = posterPath;
        this.publishYear = publishYear;
        this.description = description;
        this.genres = genres;
        this.isReady = isReady;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Collection<GenreDTO> genres) {
        this.genres = genres;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean read) {
        isReady = read;
    }
}
