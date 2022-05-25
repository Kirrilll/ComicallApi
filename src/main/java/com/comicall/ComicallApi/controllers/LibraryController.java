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

    @Autowired
    private IGenreMapper _genreMapper;

    @GetMapping("/comics")
    ResponseEntity<List<ComicsResponse>> getAll(){
        return ResponseEntity.ok().body(comicsMapper.toDtos(_libraryService.getAll()));
    }

    //не работает
    @GetMapping("/comicsByName")
    ResponseEntity<List<ComicsResponse>> geAllNameContaining(@RequestParam String prefix){
        return ResponseEntity.ok().body(comicsMapper.toDtos(_libraryService.getAllByComicsTitlePrefix(prefix)));
    }

    @GetMapping("/comicsByAuthor")
    ResponseEntity<List<ComicsResponse>> getAllByAuthorName(@RequestParam String username){
        return ResponseEntity.ok().body(comicsMapper.toDtos(_libraryService.getAllByAuthorPrefix(username)));
    }

    @PostMapping("/comics")
    ResponseEntity<List<ComicsResponse>> getAllByGenres(@RequestBody ComicsFilterRequest filterRequest){
        return ResponseEntity
                .ok()
                .body(comicsMapper.toDtos(_libraryService.getAllWithGenres(_genreMapper.toEntities(filterRequest.getGenres()))));
    }

}
