package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.Note;
import com.comicall.ComicallApi.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {

}