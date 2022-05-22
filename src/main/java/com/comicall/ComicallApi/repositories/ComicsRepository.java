package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.Comics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComicsRepository extends JpaRepository<Comics, Long> {
    Optional<Comics> findByName(String name);
}