package com.comicall.ComicallApi.services.page;

import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Note;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.mappers.page_mapper.IPageMapper;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import com.comicall.ComicallApi.repositories.NoteRepository;
import com.comicall.ComicallApi.repositories.PageRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService implements IPageService{

    @Autowired
    private UserRepository _userRepo;
    @Autowired
    private ComicsRepository _comicsRepo;
    @Autowired
    private PageRepository _pageRepository;
    @Autowired
    private NoteRepository _noteRepository;
    @Autowired
    private IPageMapper pageMapper;

    @Override
    public Note addNoteToPage(Long pageId, String note) {
        Page page = _pageRepository.getById(pageId);
        User user = getUserFromAuthentication();
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
    public List<PageResponse> getComicsPages(Long comicsId) throws ChangeSetPersister.NotFoundException {
        Comics comics = _comicsRepo.getById(comicsId);
        User user = getUserFromAuthentication();
        if(!comics.getReaders().contains(user)) throw new ChangeSetPersister.NotFoundException();
        return pageMapper.toDTOs(_pageRepository.findByComics_IdIs(comicsId, Sort.by(Sort.Direction.ASC, "pageNumber")), user);
    }


    private User getUserFromAuthentication(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return _userRepo.getById(userDetails.getId());
    }
}
