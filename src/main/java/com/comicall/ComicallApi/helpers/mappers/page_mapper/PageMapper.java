package com.comicall.ComicallApi.helpers.mappers.page_mapper;

import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.note_mapper.INoteMapper;
import com.comicall.ComicallApi.repositories.NoteRepository;
import com.comicall.ComicallApi.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PageMapper implements IPageMapper{

    @Autowired
    private INoteMapper _noteMapper;

    @Autowired
    private NoteRepository _noteRepository;

    @Override
    public PageResponse toDTO(Page page, User reader) {
        return new PageResponse(
                page.getId(),
                page.getPath(),
                _noteMapper.toDTOs(_noteRepository.findByNoteAuthor_UsernameEqualsAndNotedPage_IdEquals(reader.getUsername(), page.getId()))
        );
    }

    @Override
    public List<PageResponse> toDTOs(Collection<Page> pages, User reader) {
        return pages.stream().map(page -> toDTO(page, reader)).toList();
    }
}
