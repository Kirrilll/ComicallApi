package com.comicall.ComicallApi.services.author;

import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.page.PageRequest;
import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAuthorService {

    //Создание объединить воедино
    Optional<Comics> createComics(ComicsRequest comics);
    Optional<Comics> addGenresToComics(Long comicsId, Set<String> genres);
    Optional<Comics> addPagesToComics(PageRequest pageRequest);
    void removeComics(Long comicsId);
    Optional<Comics> changeComics(ComicsRequest comics, Long comicsId);
    List<ComicsResponse> getAuthorsComics();
    Optional<List<Page>> getComicsPages(Long id);
}
