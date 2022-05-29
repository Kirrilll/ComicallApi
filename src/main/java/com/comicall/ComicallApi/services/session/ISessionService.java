package com.comicall.ComicallApi.services.session;

import com.comicall.ComicallApi.entities.User;

public interface ISessionService {
    User getAuthenticatedUser();
}
