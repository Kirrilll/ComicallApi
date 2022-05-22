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

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(_userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(_userService.saveUser(user));
    }

    //Это в RoleController
    @PostMapping("/role/save")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<Role> createRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(_userService.saveRole(role));
    }

    //Переделать это
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/role/update")
    public  ResponseEntity<?> updateUser(@RequestBody UserToRoleRequest userToRoleRequest){
        _userService.addRoleToUser(userToRoleRequest.getUsername(), userToRoleRequest.getRoleName());
        return ResponseEntity.ok().build();
    }
}
