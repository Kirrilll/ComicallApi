package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Создать DTO userRequest, userResponse

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private IUserService _userService;

    @PostMapping("/addToLibrary")
    public ResponseEntity<MessageDTO> addComics(@RequestParam Long comicsId){
        String username = "test";
        _userService.addComicsToUserLibrary(comicsId);
        return ResponseEntity.ok().body(new MessageDTO("successfully added"));
    }

    @GetMapping("/comics")
    public ResponseEntity<List<ComicsResponse>> getUserLibrary(){
        return ResponseEntity.ok(_userService.getComics());
    }

    @PatchMapping("/removeComics")
    public ResponseEntity<MessageDTO> removeComicsFromLibrary(@RequestParam Long id){
        _userService.removeComics(id);
        return ResponseEntity.ok().body(new MessageDTO("Successfully deleted"));
    }

}
