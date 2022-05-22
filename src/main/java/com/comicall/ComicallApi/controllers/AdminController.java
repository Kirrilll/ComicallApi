package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.dtos.user.UserToRoleRequest;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.GenreMapper;
import com.comicall.ComicallApi.services.User.IAdminService;
import com.comicall.ComicallApi.services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private IUserService _userService;
    @Autowired
    private IAdminService _adminService;
    @Autowired
    private GenreMapper _genreMapper;


    @PostMapping("/admin/role/create")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(_adminService.saveRole(role));
    }

    @PostMapping("admin/genre/create")
    public ResponseEntity<?> createGenre(GenreDTO genreDTO){
        return ResponseEntity.ok().body(_adminService.saveGenre(genreDTO));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/user/update")
    public  ResponseEntity<?> updateUser(@RequestBody UserToRoleRequest userToRoleRequest){
        _adminService.addRoleToUser(userToRoleRequest.getUsername(), userToRoleRequest.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(_adminService.getUsers());
    }


}
