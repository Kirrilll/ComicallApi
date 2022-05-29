package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.*;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRepository _userRepo;
    @Autowired
    private ComicsRepository _comicsRepo;
    @Autowired
    private UsersComicsRepository _userLibraryRepo;

    @Override
    public User getUser(String username) {
        return _userRepo.findByUsername(username);
    }


    @Override
    public List<ComicsResponse> getComics() {
        User user = getUserFromAuthentication();
        return user.getUserLibrary().stream().map(
                comics -> new ComicsResponse(
                comics.getComics().getId(),
                comics.getComics().getName(),
                comics.getComics().getAuthor().getUsername(),
                comics.getComics().getPosterPath(),
                comics.getComics().getPublishYear(),
                comics.getComics().getDescription(),
                comics.getComics().getGenres().stream().map(genre -> new GenreDTO(genre.getGenre())).toList(),
                comics.getIsRead())).toList();
    }

    @Override
    public void removeComics(Long id) {
        User user = getUserFromAuthentication();
        Optional<UsersComics> usersComicsBox = _userLibraryRepo.findByUser_IdIsAndComics_IdIs(user.getId(), id);
        usersComicsBox.ifPresent(usersComics -> _userLibraryRepo.delete(usersComics));
    }

    @Override
    public void addComicsToUserLibrary(Long comicsId) {
        User user = getUserFromAuthentication();
        Optional<Comics> comics = _comicsRepo.findById(comicsId);
        comics.ifPresent(value -> _userLibraryRepo.save(new UsersComics(new UserComicsKey(user.getId(), value.getId()), user, value, false, 1)));
    }

    private User getUserFromAuthentication(){
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return _userRepo.getById(userDetails.getId());
    }

}
