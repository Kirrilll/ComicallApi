package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.services.User.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;


}
