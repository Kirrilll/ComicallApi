package com.comicall.ComicallApi.helpers.mappers.genre_mapper;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Genre;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface IGenreMapper{
    public Optional<Genre> toEntity(String genreName);
    public Set<Genre> toEntities(Collection<String> genreNames);
    public GenreDTO toDTO(Genre entity);
    public Optional<Genre> toEntity(GenreDTO dto);
}
