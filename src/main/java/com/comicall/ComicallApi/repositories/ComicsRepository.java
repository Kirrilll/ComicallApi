package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface ComicsRepository extends JpaRepository<Comics, Long> {
    Optional<Comics> findByName(String name);
    //Работает не корректно
    @Query("select c from Comics c where c.genres in :genres")
    Collection<Comics> findAllByGenresIn(@Param("genres")Set<Genre> genres);
    @Query("select c from Comics c where c.name like %:name%")
    Collection<Comics> findAllByNameContaining(@Param("name")String name);
    @Query("select c from Comics c where c.author.username like %:prefix%")
    Collection<Comics> findAllByAuthorUsernameContaining(@Param("prefix")String prefix);
}