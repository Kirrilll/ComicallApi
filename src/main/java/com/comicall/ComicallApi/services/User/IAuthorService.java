package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.entities.Comics;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;
import java.util.Set;

public interface IAuthorService {

    //Создание объединить воедино
    Optional<Comics> createComics(ComicsRequest comics);
    Optional<Comics> addGenresToComics(String comicsName, Set<String> genres);

    void removeComics(String comicsName);
    Optional<Comics> changeComics(ComicsRequest comics);

    //Добавить показать все страницы
    //Сохранить страницу
    //Удалить страницу

}
