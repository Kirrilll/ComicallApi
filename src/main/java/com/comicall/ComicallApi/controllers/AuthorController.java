package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.comics.ComicsNameGenresRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.services.User.IAuthorService;
import org.aspectj.bridge.Message;
import org.ietf.jgss.MessageProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private IAuthorService _authorService;

    @PostMapping("/author/create")
    ResponseEntity<?> createComics(@RequestBody ComicsRequest comicsRequest){
        Optional<Comics> comics = _authorService.createComics(comicsRequest);
        if(comics.isEmpty()) return ResponseEntity.status(404).body(new MessageDTO("author not found"));
        return ResponseEntity.ok(comics.get());
    }

    @DeleteMapping("/author/delete")
    ResponseEntity<?> deleteComics(String comicsTitle){
        _authorService.removeComics(comicsTitle);
        return ResponseEntity.ok().body(new MessageDTO("successfully deleted"));
    }

    @PatchMapping("author/addGenres")
    ResponseEntity<?> addGenresToComics(ComicsNameGenresRequest comicsNameGenresRequest){
        Optional<Comics> comics = _authorService.addGenresToComics(comicsNameGenresRequest.getComicsName(), comicsNameGenresRequest.getGenres());
        if(comics.isEmpty()) return ResponseEntity.status(404).body(new MessageDTO("comics not found"));
        return ResponseEntity.ok(comics.get());
    }

    @PutMapping("/author/change")
    ResponseEntity<?> changeComics(@RequestBody ComicsRequest comicsRequest){
        Optional<Comics> comics = _authorService.changeComics(comicsRequest);
        if(comics.isEmpty()) return ResponseEntity.status(404).body("Not found");
        return ResponseEntity.ok(comics.get());
    }


}
