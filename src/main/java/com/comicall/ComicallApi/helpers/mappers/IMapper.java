package com.comicall.ComicallApi.helpers.mappers;

import java.util.Optional;

public interface IMapper<T, E> {
    T toDTO(E entity);
    Optional<E> toEntity(T dto);
}
