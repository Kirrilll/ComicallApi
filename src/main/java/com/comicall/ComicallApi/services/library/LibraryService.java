package com.comicall.ComicallApi.services.library;

import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService implements ILibraryService{
    @Autowired
    private ComicsRepository _comicsRepository;


    @Override
    public List<Comics> getAll() {
        return _comicsRepository.findAll();
    }
}
