package com.comicall.ComicallApi.dtos.comics;

import java.util.Set;

public class ComicsNameGenresRequest {
    private String comicsName;
    private Set<String> genres;

    public ComicsNameGenresRequest(String comicsName, Set<String> genres) {
        this.comicsName = comicsName;
        this.genres = genres;
    }

    public String getComicsName() {
        return comicsName;
    }

    public void setComicsName(String comicsName) {
        this.comicsName = comicsName;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }
}
