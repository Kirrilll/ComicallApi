package com.comicall.ComicallApi.services.author;

import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.dtos.page.PageRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import com.comicall.ComicallApi.repositories.GenreRepository;
import com.comicall.ComicallApi.repositories.PageRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import com.comicall.ComicallApi.services.session.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService implements IAuthorService{

    @Autowired
    private ComicsRepository _comicsRepository;
    @Autowired
    private IGenreMapper _genreMapper;
    @Autowired
    private PageRepository _pageRepository;
    @Autowired
    private ISessionService _sessionService;

    @Override
    public Optional<Comics> createComics(ComicsRequest comics) {

        //Заменить это на доставание из контекста
        User author = _sessionService.getAuthenticatedUser();

        Comics createdComics = Comics.builder()
                .setName(comics.getName())
                .setAuthor(author)
                .setDescription(comics.getDescription())
                .setPosterPath(comics.getPosterPath())
                .setPublishYear(comics.getPublishYear())
                .setGenres(_genreMapper.toEntities(comics.getGenres()))
                .build();


        Comics savedComics = _comicsRepository.save(createdComics);

        Set<Page> pages = new HashSet<>();
        for(PageRequest pageRequest: comics.getPages()){
            pages.add(_pageRepository.save(new Page(
                    null,
                    pageRequest.getPageNumber(),
                    pageRequest.getFilePath(),
                    savedComics,
                    new HashSet<>()
            )));
        }
        savedComics.setPages(pages);
        return Optional.of( _comicsRepository.save(createdComics));
    }

    @Override
    public Optional<Comics> addGenresToComics(String comicsName, Set<String> genres)  {
        Optional<Comics> comicsOptional = _comicsRepository.findByName(comicsName);
        if(comicsOptional.isEmpty()) return Optional.empty();
        Comics comics = comicsOptional.get();
        User author = _sessionService.getAuthenticatedUser();
        if(!comics.getAuthor().equals(author)) return  Optional.empty();
        Set<Genre> comicsGenres = comics.getGenres();
        comicsGenres.addAll(_genreMapper.toEntities(genres));
        comics.setGenres(comicsGenres);
        return Optional.of(_comicsRepository.save(comics));
    }



    @Override
    public void removeComics(String comicsName) {
        //также здесь очистка пространства от картинок
        Optional<Comics> comics = _comicsRepository.findByName(comicsName);
        if(!comics.get().getAuthor().equals(_sessionService.getAuthenticatedUser())) return;
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
