package com.comicall.ComicallApi.helpers.mappers.genre_mapper;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenreMapper implements IGenreMapper{

    @Autowired
    private GenreRepository _genreRepository;

    @Override
    public GenreDTO toDTO(Genre entity) {
        return new GenreDTO(entity.getGenre());
    }

    @Override
    public Optional<Genre> toEntity(GenreDTO dto) {
        return _genreRepository.findByGenre(dto.getName());
    }

    public Optional<Genre> toEntity(String genreName){
        return _genreRepository.findByGenre(genreName);
    }

    public Set<Genre> toEntities(Collection<String> genreNames){
        return genreNames.stream()
                .filter(genre -> toEntity(genre).isPresent())
                .map(genre -> toEntity(genre).get())
                .collect(Collectors.toSet());
    }
}
