package com.comicall.ComicallApi.services.library;

import com.comicall.ComicallApi.dtos.comics.ComicsFilterRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryService implements ILibraryService{
    @Autowired
    private ComicsRepository _comicsRepository;

    @Autowired
    private IGenreMapper _genreMapper;

    @Override
    public List<Comics> getFilteredComics(ComicsFilterRequest comicsFilterRequest) {

        List<Comics> comics = new ArrayList<>();
        if(comicsFilterRequest.getIsSearchByName()){
            comics.addAll(_comicsRepository.findByNameIsContaining(comicsFilterRequest.getPrefix()));
        }
        else{
            comics.addAll(_comicsRepository.findAllByAuthorUsernameContaining(comicsFilterRequest.getPrefix()));
        }
        if(comicsFilterRequest.getGenres() == null) return comics;
        Set<Genre> genres = new HashSet<>(_genreMapper.toEntities(comicsFilterRequest.getGenres()));
        if(genres.size() == 0) return comics;
        return comics.stream().filter(com -> {
            Set<Genre> intersection =  new HashSet<Genre>(com.getGenres());
            intersection.retainAll(genres);
            return intersection.size() == genres.size();
        }).toList();
    }


}
