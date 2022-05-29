package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Note;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface IUserService {
    User getUser(String username);
    List<ComicsResponse> getComics();
    void removeComics(Long id);
    void addComicsToUserLibrary(Long id);
}
