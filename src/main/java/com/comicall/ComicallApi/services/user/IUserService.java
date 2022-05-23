package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface IUserService {
    User saveUser(User user );
    User getUser(String username);
    List<ComicsResponse> getComics();
    void removeComics(Long id);
    void addComicsToUserLibrary(String username, Long id) throws ChangeSetPersister.NotFoundException;
}
