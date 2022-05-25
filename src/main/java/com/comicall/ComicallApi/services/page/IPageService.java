package com.comicall.ComicallApi.services.page;

import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Note;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface IPageService {
    Note addNoteToPage(Long pageId, String note);
    void deleteNote(Long noteId);
    Note changeNote(Long noteId, String note);
    List<PageResponse> getComicsPages(Long comicsId) throws ChangeSetPersister.NotFoundException;
}
