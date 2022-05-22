package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.GenreMapper;
import com.comicall.ComicallApi.repositories.GenreRepository;
import com.comicall.ComicallApi.repositories.RoleRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private RoleRepository _roleRepository;
    @Autowired
    private GenreRepository _genreRepository;
    @Autowired
    private GenreMapper _genreMapper;

    @Override
    public Role saveRole(Role role) {
        return _roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = _userRepository.findByUsername(username);
        Role role = _roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public List<User> getUsers() {
        return _userRepository.findAll();
    }

    @Override
    public Genre saveGenre(GenreDTO genreDTO) {
        return _genreRepository.save(new Genre(null, genreDTO.getName(), new HashSet<>()));
    }
}
