package com.comicall.ComicallApi.controllers;


import com.comicall.ComicallApi.dtos.comics.ComicsFilterRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.helpers.mappers.comics_mapper.IComicsMapper;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import com.comicall.ComicallApi.services.library.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class LibraryController {

    @Autowired
    private ILibraryService _libraryService;

    @Autowired
    private IComicsMapper comicsMapper;

    @PostMapping("/comics")
    ResponseEntity<List<ComicsResponse>> getAllByFilter(@RequestBody ComicsFilterRequest filterRequest){

        return ResponseEntity
                .ok()
                .body(comicsMapper.toDtos(_libraryService.getFilteredComics(filterRequest)));
    }

    @GetMapping("/genres")

    ResponseEntity<List<GenreDTO>> getAllGenres(){
        return ResponseEntity.ok(_libraryService.getAllGenres());
    }



}
