package com.comicall.ComicallApi.helpers.mappers.comics_mapper;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.entities.Comics;

import java.util.Collection;
import java.util.List;

public interface IComicsMapper {
    ComicsResponse toDto(Comics comics);

    List<ComicsResponse> toDtos(Collection<Comics> comics);
}
