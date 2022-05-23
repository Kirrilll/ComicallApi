package com.comicall.ComicallApi.dtos.genres;

public class GenreDTO {
    private String name;

    public  GenreDTO() {}
    public GenreDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
