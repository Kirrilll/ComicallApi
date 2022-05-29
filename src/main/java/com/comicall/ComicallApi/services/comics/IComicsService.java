package com.comicall.ComicallApi.services.comics;

import com.comicall.ComicallApi.dtos.comics.ComicsReadResponse;
import com.comicall.ComicallApi.entities.Note;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface IComicsService {
    Note addNoteToPage(Long pageId, String note);
    void deleteNote(Long noteId);
    Note changeNote(Long noteId, String note);
    ComicsReadResponse getComicsPages(Long comicsId) throws ChangeSetPersister.NotFoundException;
    int setBookmark(int pageNumber, Long comicsId) throws Exception;

}
