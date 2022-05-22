package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.dtos.comics.ComicsCreateRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.entities.enums.EGenre;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import com.comicall.ComicallApi.repositories.GenreRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService implements IAuthorService{

    @Autowired
    private ComicsRepository _comicsRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private GenreRepository _genreRepository;

    @Override
    public Comics createComics(ComicsCreateRequest comics) throws ChangeSetPersister.NotFoundException {

        Optional<User> author = Optional.ofNullable(_userRepository.findByUsername(comics.getAuthorName()));
        if(author.isEmpty()) throw new ChangeSetPersister.NotFoundException();

        Comics createdComics = Comics.builder()
                .setName(comics.getName())
                .setAuthor(author.get())
                .setDescription(comics.getDescription())
                .setPosterPath(comics.getPosterPath())
                .setPublishYear(comics.getPublishYear())
                .build();

        return _comicsRepository.save(createdComics);
    }

    //Поиск по имени и по String genres
    @Override
    public Comics addGenresToComics(String comicsName, Set<EGenre> genres) throws ChangeSetPersister.NotFoundException {
        Optional<Comics> comicsOptional = _comicsRepository.findByName(comicsName);
        if(comicsOptional.isEmpty()) throw new ChangeSetPersister.NotFoundException();
        Set<Genre> existsGenres = genres.stream().map(genre -> {
            Optional<Genre> foundedGenre = _genreRepository.findByGenre(genre);
            return foundedGenre.get();
        }).collect(Collectors.toSet());
        Comics comics = comicsOptional.get();
        Set<Genre> comicsGenres = comics.getGenres();
        comicsGenres.addAll(existsGenres);
        comics.setGenres(comicsGenres);
        return _comicsRepository.save(comics);
    }

    //Сделать GenreRequest
    @Override
    public Comics createComicsWithGenres(Comics comics, Set<Genre> genres) {
        return null;
    }
}
