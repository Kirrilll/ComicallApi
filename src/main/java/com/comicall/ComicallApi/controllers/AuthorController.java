package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.comics.ComicsNameGenresRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.services.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/author")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private IAuthorService _authorService;

    @PostMapping("/create")
    ResponseEntity<?> createComics(@RequestBody ComicsRequest comicsRequest){
        Optional<Comics> comics = _authorService.createComics(comicsRequest);
        if(comics.isEmpty()) return ResponseEntity.status(404).body(new MessageDTO("author not found"));
        return ResponseEntity.ok(comics.get());
    }

    @DeleteMapping("/delete")
    ResponseEntity<MessageDTO> deleteComics(@RequestParam String comicsTitle){
        _authorService.removeComics(comicsTitle);
        return ResponseEntity.ok().body(new MessageDTO("successfully deleted"));
    }

    @PatchMapping("/addGenres")
    ResponseEntity<?> addGenresToComics(@RequestBody ComicsNameGenresRequest comicsNameGenresRequest){
        Optional<Comics> comics = _authorService.addGenresToComics(comicsNameGenresRequest.getComicsName(), comicsNameGenresRequest.getGenres());
        if(comics.isEmpty()) return ResponseEntity.status(404).body(new MessageDTO("comics not found"));
        return ResponseEntity.ok(comics.get());
    }

    @PutMapping("/change")
    ResponseEntity<?> changeComics(@RequestBody ComicsRequest comicsRequest){
        Optional<Comics> comics = _authorService.changeComics(comicsRequest);
        if(comics.isEmpty()) return ResponseEntity.status(404).body("Not found");
        return ResponseEntity.ok(comics.get());
    }


}
