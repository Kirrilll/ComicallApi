package com.comicall.ComicallApi.services.auth;

import com.comicall.ComicallApi.dtos.user.JwtResponse;
import com.comicall.ComicallApi.dtos.user.RegisterRequest;
import com.comicall.ComicallApi.entities.User;

import java.util.Optional;

public interface IAuthService {
    Optional<User> registerUser(RegisterRequest registerRequest);
    JwtResponse signIn(RegisterRequest loginRequest);
    User registerAuthor(RegisterRequest registerRequest);
}
