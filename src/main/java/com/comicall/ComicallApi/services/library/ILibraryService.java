package com.comicall.ComicallApi.services.library;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ILibraryService {
    List<Comics> getAll();

    Collection<Comics> getAllByAuthorPrefix(String prefix);
    Collection<Comics> getAllByComicsTitlePrefix(String prefix);
    Collection<Comics> getAllWithGenres(Set<Genre> genres);
    Collection<Comics> sortByReadersCount();
    //Взять определенное количество(пагинация)
    //Сортировка
}
