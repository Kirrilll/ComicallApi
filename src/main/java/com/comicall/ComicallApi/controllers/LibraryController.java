package com.comicall.ComicallApi.controllers;


import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import com.comicall.ComicallApi.services.library.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class LibraryController {
    @Autowired
    private ILibraryService _libraryService;

    @Autowired
    private IGenreMapper _genreMapper;

    @GetMapping("/comics")
    ResponseEntity<List<ComicsResponse>> getAll(){
        List<Comics> allComics = _libraryService.getAll();
        return ResponseEntity.ok().body(allComics.stream().map(
                comics -> new ComicsResponse(
                        comics.getId(),
                        comics.getName(),
                        comics.getAuthor().getUsername(),
                        comics.getPosterPath(),
                        comics.getPublishYear(),
                        comics.getDescription(),
                        comics.getGenres().stream().map(genre -> new GenreDTO(genre.getGenre())).toList()
                )).toList());
    }

}
