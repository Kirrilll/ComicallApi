package com.comicall.ComicallApi.dtos.comics;
public class ComicsUserResponse {
    private Long id;
    private String name;
    private String authorName;
    private String posterPath;
    private int pagesRead;
    private int pagesAll;

    public ComicsUserResponse(){}

    public ComicsUserResponse(Long id, String name, String authorName, String posterPath, int pagesRead, int pagesAll) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.posterPath = posterPath;
        this.pagesRead = pagesRead;
        this.pagesAll = pagesAll;
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

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public int getPagesAll() {
        return pagesAll;
    }

    public void setPagesAll(int pagesAll) {
        this.pagesAll = pagesAll;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
