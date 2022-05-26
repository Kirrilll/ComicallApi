package com.comicall.ComicallApi.services.library;
import com.comicall.ComicallApi.dtos.comics.ComicsFilterRequest;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ILibraryService {
    List<Comics> getFilteredComics(ComicsFilterRequest comicsFilterRequest);
}
