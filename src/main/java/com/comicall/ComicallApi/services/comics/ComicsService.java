package com.comicall.ComicallApi.services.comics;

import com.comicall.ComicallApi.dtos.comics.ComicsReadResponse;
import com.comicall.ComicallApi.entities.*;
import com.comicall.ComicallApi.helpers.mappers.page_mapper.IPageMapper;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.*;
import com.comicall.ComicallApi.services.session.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComicsService implements IComicsService {

    @Autowired
    private ISessionService _sessionService;
    @Autowired
    private PageRepository _pageRepository;
    @Autowired
    private NoteRepository _noteRepository;
    @Autowired
    private IPageMapper pageMapper;
    @Autowired
    private UsersComicsRepository _usersComicsRepository;

    @Override
    public Note addNoteToPage(Long pageId, String note) {
        Page page = _pageRepository.getById(pageId);
        User user = _sessionService.getAuthenticatedUser();
        return _noteRepository.save(new Note(null, page, note, user));
    }

    @Override
    public void deleteNote(Long noteId) {
        _noteRepository.delete(_noteRepository.getById(noteId));
    }

    @Override
    public Note changeNote(Long noteId, String note) {
        Note existNote = _noteRepository.getById(noteId);
        existNote.setNote(note);
        return _noteRepository.save(existNote);
    }

    @Override
    public ComicsReadResponse getComicsPages(Long comicsId) throws ChangeSetPersister.NotFoundException {
        User user = _sessionService.getAuthenticatedUser();

        Optional<UsersComics> usersComicsBox = _usersComicsRepository.findByUser_IdIsAndComics_IdIs(user.getId(), comicsId);
        if(usersComicsBox.isEmpty()) throw new ChangeSetPersister.NotFoundException();

        return new ComicsReadResponse(
                pageMapper.toDTOs(_pageRepository.findByComics_IdIs(comicsId, Sort.by(Sort.Direction.ASC, "pageNumber")), user),
                usersComicsBox.get().getMarkedPage()
        );
    }

    @Override
    public int setBookmark(int pageNumber, Long comicsId) throws Exception {
        User user = _sessionService.getAuthenticatedUser();
        Optional<UsersComics> usersComicsBox = _usersComicsRepository.findByUser_IdIsAndComics_IdIs(user.getId(), comicsId);
        if(usersComicsBox.isEmpty()) throw new ChangeSetPersister.NotFoundException();
        UsersComics usersComics = usersComicsBox.get();
        if(usersComics.getComics().getPages().size() < pageNumber) throw new IndexOutOfBoundsException();
        usersComics.setMarkedPage(pageNumber);
        if(usersComics.getComics().getPages().size() == pageNumber) usersComics.setIsRead(true);
        _usersComicsRepository.save(usersComics);
        return pageNumber;
    }
}
