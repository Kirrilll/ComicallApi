package com.comicall.ComicallApi.configs;

import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class AuthenticatedConfig {

    @Autowired
    private UserRepository _userRepository;

//    @Bean
//    @Scope("prototype")
//    private User authenticatedUserBean(){
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return _userRepository.getById(userDetails.getId());
//    }
}
