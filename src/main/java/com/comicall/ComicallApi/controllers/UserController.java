package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Создать DTO userRequest, userResponse

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private IUserService _userService;

    @PostMapping("/user/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(_userService.saveUser(user));
    }

    @PostMapping("/user/addToLibrary")
    public ResponseEntity<MessageDTO> addComics(@RequestParam Long comicsId){
        String username = "test";
        try {
            _userService.addComicsToUserLibrary("test", comicsId);
            return ResponseEntity.ok().body(new MessageDTO("successfully added"));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDTO("successfully added"));
        }
    }

    @GetMapping("/user/comics")
    public ResponseEntity<List<ComicsResponse>> getUserLibrary(){
        return ResponseEntity.ok(_userService.getComics());
    }

    @PatchMapping("users/removeComics")
    public ResponseEntity<MessageDTO> removeComicsFromLibrary(@RequestParam Long id){
        _userService.removeComics(id);
        return ResponseEntity.ok().body(new MessageDTO("Successfully deleted"));
    }

    //Добавить в свою библиотеку
    //удалить из библиотеки
    //добавить заметку
    //удалить заметку
    //посмотреть личную библиотеку?


}
