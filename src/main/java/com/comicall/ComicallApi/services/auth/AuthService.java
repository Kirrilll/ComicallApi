package com.comicall.ComicallApi.services.auth;

import com.comicall.ComicallApi.dtos.user.JwtResponse;
import com.comicall.ComicallApi.dtos.user.RegisterRequest;
import com.comicall.ComicallApi.entities.User;

public class AuthService implements IAuthService{
    @Override
    public User registerUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public JwtResponse signIn(RegisterRequest loginRequest) {
        return null;
    }

    @Override
    public User registerAuthor(RegisterRequest registerRequest) {
        return null;
    }
}
