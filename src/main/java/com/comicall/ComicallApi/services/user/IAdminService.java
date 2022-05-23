package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;

import java.util.List;

public interface IAdminService {
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    List<User> getUsers();
    Genre saveGenre(GenreDTO genreDTO);
}
