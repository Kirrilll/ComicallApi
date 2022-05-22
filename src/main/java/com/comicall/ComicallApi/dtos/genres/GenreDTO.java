package com.comicall.ComicallApi.dtos.genres;

public class GenreDTO {
    private final String name;

    public GenreDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
