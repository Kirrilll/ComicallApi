package com.comicall.ComicallApi.helpers.mappers.page_mapper;

import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;

import java.util.Collection;
import java.util.List;

public interface IPageMapper {
    PageResponse toDTO(Page page, User reader);
    List<PageResponse> toDTOs(Collection<Page> pages, User reader);
}
