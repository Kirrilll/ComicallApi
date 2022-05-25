package com.comicall.ComicallApi.dtos.comics;

import java.util.Set;

public class ComicsFilterRequest {
    private Set<String> genres;
    private String prefix;
    private boolean isSearchByName;

    public ComicsFilterRequest(){}

    public ComicsFilterRequest(Set<String> genres, String prefix, boolean isSearchByName) {
        this.genres = genres;
        this.prefix = prefix;
        this.isSearchByName = isSearchByName;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isSearchByName() {
        return isSearchByName;
    }

    public void setSearchByName(boolean searchByName) {
        isSearchByName = searchByName;
    }
}
