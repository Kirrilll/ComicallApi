package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsUserResponse;
import com.comicall.ComicallApi.entities.*;
import com.comicall.ComicallApi.helpers.mappers.comics_mapper.IComicsMapper;
import com.comicall.ComicallApi.repositories.*;
import com.comicall.ComicallApi.services.session.ISessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements  IUserService{

    @Autowired
    private ComicsRepository _comicsRepo;
    @Autowired
    private UsersComicsRepository _userLibraryRepo;
    @Autowired
    private ISessionService _sessionService;
    @Autowired
    private NoteRepository _noteRepo;

    @Override
    public List<ComicsUserResponse> getComics() {
        User user = _sessionService.getAuthenticatedUser();
        List<UsersComics> usersComics= _userLibraryRepo.findByUser_IdIs(user.getId());
        return usersComics.stream().map(comics -> new ComicsUserResponse(
                comics.getComics().getId(),
                comics.getComics().getName(),
                comics.getComics().getAuthor().getUsername(),
                comics.getComics().getPosterPath(),
                comics.getMarkedPage(),
                comics.getComics().getPages().size()
        )).toList() ;
    }

    @Override
    public ComicsUserResponse removeComics(Long id) {
        User user = _sessionService.getAuthenticatedUser();
        Optional<UsersComics> usersComicsBox = _userLibraryRepo.findByUser_IdIsAndComics_IdIs(user.getId(), id);
        usersComicsBox.ifPresent(usersComics -> _userLibraryRepo.delete(usersComics));
        _noteRepo.deleteByNotedPage_Comics_IdIsAndNoteAuthor_IdIs(id, user.getId());
        ComicsUserResponse comics = new ComicsUserResponse();
        comics.setId(usersComicsBox.get().getComics().getId());
        comics.setName(usersComicsBox.get().getComics().getName());
        comics.setAuthorName(usersComicsBox.get().getComics().getAuthor().getUsername());
        comics.setPagesAll(usersComicsBox.get().getComics().getPages().size());
        comics.setPagesRead(usersComicsBox.get().getMarkedPage());
        comics.setPosterPath(usersComicsBox.get().getComics().getPosterPath());
        return comics;
    }

    @Override
    public ComicsUserResponse addComicsToUserLibrary(Long comicsId) {
        User user = _sessionService.getAuthenticatedUser();
        Optional<Comics> comics = _comicsRepo.findById(comicsId);
        UsersComics userComics = new UsersComics();
        if(comics.isPresent()){
            userComics = _userLibraryRepo.save(new UsersComics(new UserComicsKey(user.getId(), comics.get().getId()), user, comics.get(), false, 0));
        }
        ComicsUserResponse response = new ComicsUserResponse();
        response.setId(userComics.getComics().getId());
        response.setName(userComics.getComics().getName());
        response.setAuthorName(userComics.getComics().getAuthor().getUsername());
        response.setPagesAll(userComics.getComics().getPages().size());
        response.setPagesRead(userComics.getMarkedPage());
        response.setPosterPath((userComics.getComics().getPosterPath()));


        return response;
    }
}
