package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.comics.ComicsNameGenresRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.page.PageRequest;
import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.services.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private IAuthorService _authorService;

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> createComics(@ModelAttribute ComicsRequest comicsRequest){
        Optional<Comics> comics = _authorService.createComics(comicsRequest);
        if(comics.isEmpty()) return ResponseEntity.status(404).body(new MessageDTO("author not found"));
        return ResponseEntity.ok(comics.get());
    }

    @PatchMapping(value = "/comics/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> addPagesToComics(@ModelAttribute PageRequest pageRequest){
        Optional<Comics> comicsBox = _authorService.addPagesToComics(pageRequest);
        if(comicsBox.isEmpty()) return ResponseEntity.badRequest().body(new MessageDTO("bad request"));
        return ResponseEntity.ok().body(comicsBox.get());
    }

    @PutMapping(value = "/change/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> changeComics(@ModelAttribute ComicsRequest comicsRequest, @PathVariable Long id){
        Optional<Comics> comics = _authorService.changeComics(comicsRequest, id);
        if(comics.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO("comics not found"));
        return ResponseEntity.ok(comics.get());
    }

    @DeleteMapping("/delete")
    ResponseEntity<MessageDTO> deleteComics(@RequestParam Long comicsId){
        _authorService.removeComics(comicsId);
        return ResponseEntity.ok().body(new MessageDTO("successfully deleted"));
    }

    @PatchMapping("/addGenres")
    ResponseEntity<?> addGenresToComics(@RequestBody ComicsNameGenresRequest comicsNameGenresRequest){
        Optional<Comics> comics = _authorService.addGenresToComics(comicsNameGenresRequest.getComicsId(), comicsNameGenresRequest.getGenres());
        if(comics.isEmpty()) return ResponseEntity.status(404).body(new MessageDTO("comics not found"));
        return ResponseEntity.ok(comics.get());
    }



    @GetMapping("/comics")
    ResponseEntity<List<ComicsResponse>> getAuthorComics(){
        return ResponseEntity.ok().body(_authorService.getAuthorsComics());
    }

    @GetMapping("comics/{id}")
    ResponseEntity<?> getComicsPages(@PathVariable Long id){
        Optional<List<Page>> pages = _authorService.getComicsPages(id);
        if(pages.isEmpty()) return ResponseEntity.badRequest().body(new MessageDTO("You aren't author of this comics"));
        return ResponseEntity.ok().body(pages.get());
    }


}
