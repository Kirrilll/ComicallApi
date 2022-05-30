package com.comicall.ComicallApi.dtos.comics;

import java.util.Set;

public class ComicsNameGenresRequest {
    private Long comicsId;
    private Set<String> genres;

    public ComicsNameGenresRequest(Long comicsId, Set<String> genres) {
        this.comicsId = comicsId;
        this.genres = genres;
    }

    public Long getComicsId() {
        return comicsId;
    }

    public void setComicsId(Long comicsId) {
        this.comicsId = comicsId;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }
}
