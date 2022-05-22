package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user );
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
