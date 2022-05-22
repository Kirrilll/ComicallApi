package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.dtos.comics.ComicsCreateRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.enums.EGenre;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Set;

public interface IAuthorService {

    Comics createComics(ComicsCreateRequest comics) throws ChangeSetPersister.NotFoundException;
    Comics addGenresToComics(String comicsName, Set<EGenre> genres) throws ChangeSetPersister.NotFoundException;
    Comics createComicsWithGenres(Comics comics, Set<Genre> genres);
}
