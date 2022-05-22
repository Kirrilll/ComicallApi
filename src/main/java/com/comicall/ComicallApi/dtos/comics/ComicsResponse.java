package com.comicall.ComicallApi.dtos.comics;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;

import java.util.Collection;

public class ComicsResponse {
    private final String name;
    private final String authorName;
    private final String posterPath;
    private final int publishYear;
    private final String description;
    private final Collection<GenreDTO> genres;

    public ComicsResponse(String name, String authorName, String posterPath, int publishYear, String description, Collection<GenreDTO> genres) {
        this.name = name;
        this.authorName = authorName;
        this.posterPath = posterPath;
        this.publishYear = publishYear;
        this.description = description;
        this.genres = genres;
    }
}
