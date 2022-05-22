package com.comicall.ComicallApi.dtos.comics;

public class ComicsCreateRequest {
    private String name;
    private String description;
    private int publishYear;
    private String posterPath;
    private String authorName;

    public  ComicsCreateRequest(){}

    public ComicsCreateRequest(String name, String description, int publishYear, String posterPath, String authorName) {
        this.name = name;
        this.description = description;
        this.publishYear = publishYear;
        this.posterPath = posterPath;
        this.authorName = authorName;
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
}
