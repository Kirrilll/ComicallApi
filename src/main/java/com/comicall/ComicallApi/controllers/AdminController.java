package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.dtos.user.UserToRoleRequest;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.GenreMapper;
import com.comicall.ComicallApi.services.admin.IAdminService;
import com.comicall.ComicallApi.services.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private IAdminService _adminService;


    @PostMapping("/role/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(_adminService.saveRole(role));
    }

    @PostMapping("/genre/create")
    public ResponseEntity<?> createGenre(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.ok().body(_adminService.saveGenre(genreDTO));
    }

    @PutMapping("/user/update")
    public  ResponseEntity<?> updateUser(@RequestBody UserToRoleRequest userToRoleRequest){
        _adminService.addRoleToUser(userToRoleRequest.getUsername(), userToRoleRequest.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(_adminService.getUsers());
    }


}
