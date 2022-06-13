package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.comics.ComicsUserResponse;
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
    public ResponseEntity<ComicsUserResponse> addComics(@RequestParam Long comicsId){
        return ResponseEntity.ok().body( _userService.addComicsToUserLibrary(comicsId));
    }

    @GetMapping("/comics")
    public ResponseEntity<List<ComicsUserResponse>> getUserLibrary(){
        return ResponseEntity.ok(_userService.getComics());
    }

    @PatchMapping("/removeComics")
    public ResponseEntity<ComicsUserResponse> removeComicsFromLibrary(@RequestParam Long id){
        return ResponseEntity.ok().body(_userService.removeComics(id));
    }

}
