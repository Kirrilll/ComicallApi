package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.user.UserToRoleRequest;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //Добавить в свою библиотеку
    //удалить из библиотеки
    //добавить заметку
    //удалить заметку
    //посмотреть личную библиотеку?


}
