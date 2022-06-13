package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.comics.ComicsUserResponse;
import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Note;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface IUserService {
    List<ComicsUserResponse> getComics();
    ComicsUserResponse removeComics(Long id);
    ComicsUserResponse addComicsToUserLibrary(Long id);
}
