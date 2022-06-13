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
    Optional<ComicsResponse> createComics(ComicsRequest comics);
    Optional<ComicsResponse> addGenresToComics(Long comicsId, Set<String> genres);
    Optional<ComicsResponse> addPagesToComics(PageRequest pageRequest);
    void removeComics(Long comicsId);
    Optional<ComicsResponse> changeComics(ComicsRequest comics, Long comicsId);
    List<ComicsResponse> getAuthorsComics();
    Optional<List<Page>> getComicsPages(Long id);
    ComicsResponse publishComics(Long id, boolean isRead);
}
