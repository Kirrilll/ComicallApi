package com.comicall.ComicallApi.services.session;

import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements ISessionService{

    @Autowired
    private UserRepository _userRepository;

    @Override
    public User getAuthenticatedUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return _userRepository.getById(userDetails.getId());
    }
}
