package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User saveUser(User user );
    User getUser(String username);
    List<ComicsResponse> getComics();
    void addComicsToUserLibrary(String username, String comicsTitle) throws ChangeSetPersister.NotFoundException;
}
