package com.comicall.ComicallApi.services.library;
import com.comicall.ComicallApi.entities.Comics;

import java.util.List;

public interface ILibraryService {
    List<Comics> getAll();

    //Взять определенное количество(пагинация)
    //Сортировка
}
