package com.comicall.ComicallApi.helpers.mappers.comics_mapper;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ComicsMapper implements IComicsMapper{

    @Autowired
    IGenreMapper genreMapper;

    @Override
    public ComicsResponse toDto(Comics comics) {
        return new ComicsResponse(
                comics.getId(),
                comics.getName(),
                comics.getAuthor().getUsername(),
                comics.getPosterPath(),
                comics.getPublishYear(),
                comics.getDescription(),
                comics.getGenres().stream().map(genre -> new GenreDTO(genre.getGenre())).toList());
    }

    @Override
    public List<ComicsResponse> toDtos(Collection<Comics> comics) {
        return comics.stream().map(this::toDto).toList();
    }
}
