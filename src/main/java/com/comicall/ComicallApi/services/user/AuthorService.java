package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import com.comicall.ComicallApi.repositories.GenreRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthorService implements IAuthorService{

    @Autowired
    private ComicsRepository _comicsRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private GenreRepository _genreRepository;
    @Autowired
    private IGenreMapper _genreMapper;

    @Override
    public Optional<Comics> createComics(ComicsRequest comics) {

        //Заменить это на доставание из контекста
        Optional<User> author = Optional.ofNullable(_userRepository.findByUsername(comics.getAuthorName()));
        if(author.isEmpty()) return Optional.empty();

        Comics createdComics = Comics.builder()
                .setName(comics.getName())
                .setAuthor(author.get())
                .setDescription(comics.getDescription())
                .setPosterPath(comics.getPosterPath())
                .setPublishYear(comics.getPublishYear())
                .setGenres(_genreMapper.toEntities(comics.getGenres()))
                .build();


        return Optional.of( _comicsRepository.save(createdComics));
    }

    @Override
    public Optional<Comics> addGenresToComics(String comicsName, Set<String> genres)  {
        Optional<Comics> comicsOptional = _comicsRepository.findByName(comicsName);
        if(comicsOptional.isEmpty()) return Optional.empty();
        Comics comics = comicsOptional.get();
        Set<Genre> comicsGenres = comics.getGenres();
        comicsGenres.addAll(_genreMapper.toEntities(genres));
        comics.setGenres(comicsGenres);
        return Optional.of(_comicsRepository.save(comics));
    }



    @Override
    public void removeComics(String comicsName) {
        //также здесь очистка пространства от картинок
        Optional<Comics> comics = _comicsRepository.findByName(comicsName);
        comics.ifPresent(value -> _comicsRepository.delete(value));
    }

    @Override
    public Optional<Comics> changeComics(ComicsRequest comicsRequest) {
        Optional<Comics> comicsOptional = _comicsRepository.findByName(comicsRequest.getName());
        if(comicsOptional.isEmpty()) return Optional.empty();
        Comics comics = comicsOptional.get();
        comics.setDescription(comicsRequest.getDescription());
        comics.setName(comicsRequest.getDescription());
        comics.setPublishYear(comicsRequest.getPublishYear());
        comics.setGenres(_genreMapper.toEntities(comicsRequest.getGenres()));
        return Optional.of(_comicsRepository.save(comics));
    }
}
