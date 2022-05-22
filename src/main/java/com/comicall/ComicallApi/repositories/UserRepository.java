package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}