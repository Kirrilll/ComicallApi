package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.UserComicsKey;
import com.comicall.ComicallApi.entities.UsersComics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersComicsRepository extends JpaRepository<UsersComics, UserComicsKey> {
    @Query("select u from UsersComics u where u.user.id = ?1 and u.comics.id = ?2")
    Optional<UsersComics> findByUser_IdIsAndComics_IdIs(Long userId, Long comicsId);
}