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
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ComicsRepository extends JpaRepository<Comics, Long> {
    Optional<Comics> findByName(String name);

    @Query("select c from Comics c where c.name like %:prefix%")
    List<Comics> findByNameIsContaining(@Param("prefix") String name);

    @Query("select c from Comics c where c.name like %:prefix% and c.isReady = true")
    List<Comics> findByNameContainingAndIsReadyIsTrue(@Param("prefix") String name);

    List<Comics> findByAuthor_UsernameIsAndIsReadyIsTrue(String username);




    @Query("select c from Comics c where c.author.username like %:prefix%")
    Collection<Comics> findAllByAuthorUsernameContaining(@Param("prefix")String prefix);

    List<Comics> findByAuthor_IdEquals(Long id);


}