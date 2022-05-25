package com.comicall.ComicallApi.services.library;

import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LibraryService implements ILibraryService{
    @Autowired
    private ComicsRepository _comicsRepository;


    @Override
    public List<Comics> getAll() {
        return _comicsRepository.findAll();
    }

    @Override
    public Collection<Comics> getAllByAuthorPrefix(String prefix) {
        return _comicsRepository.findAllByAuthorUsernameContaining(prefix);
    }

    @Override
    public Collection<Comics> getAllByComicsTitlePrefix(String prefix) {
        return _comicsRepository.findAllByNameContaining(prefix);
    }

    @Override
    public Collection<Comics> getAllWithGenres(Set<Genre> genres) {
        return _comicsRepository.findAllByGenresIn(genres);
    }

//    public Collection<Comics> getFiltred(){
//        Set<Comics> intersection = new HashSet<>(getAll());
//        intersection.retainAll(getAllWithGenres())
//        return intersection
//    }


    @Override
    public Collection<Comics> sortByReadersCount() {
        //return _comicsRepository.findAllOrderByReadersCount();
        return null;
    }


}
