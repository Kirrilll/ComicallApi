package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.configs.jwt.JwtUtils;
import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.user.RegisterRequest;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.repositories.RoleRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import com.comicall.ComicallApi.services.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private IAuthService _authService;


    @PostMapping("/signing")
    public ResponseEntity<?> authUser(@RequestBody RegisterRequest loginRequest) {
        return ResponseEntity.ok(_authService.signIn(loginRequest));
    }

    @PostMapping("/signupUser")
    public ResponseEntity<MessageDTO> registerUserAsReader(@RequestBody RegisterRequest signupRequest) {
        Optional<User> user = _authService.registerUser(signupRequest);
        if(user.isEmpty()) return ResponseEntity.badRequest().body(new MessageDTO("user this username is already exist"));
        return ResponseEntity.ok(new MessageDTO("User CREATED"));
    }

    @PostMapping("/signupAuthor")
    public ResponseEntity<?> registerAsAuthor(@RequestBody RegisterRequest signupRequest){
        Optional<User> user =  _authService.registerAuthor(signupRequest);
        if(user.isEmpty()) return ResponseEntity.badRequest().body(new MessageDTO("user this username is already exist"));
        return ResponseEntity.ok(user.get());
    }
}
