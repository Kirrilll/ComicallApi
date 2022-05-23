package com.comicall.ComicallApi.services.user;

import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import com.comicall.ComicallApi.repositories.RoleRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements  IUserService, UserDetailsService {

    @Autowired
    private UserRepository _userRepo;
    @Autowired
    private RoleRepository _roleRepo;
    @Autowired
    private ComicsRepository _comicsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return _userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        return _userRepo.findByUsername(username);
    }


    @Override
    public List<ComicsResponse> getComics() {
        String name = "test"; //Будет получаться из контекста
        User user = _userRepo.findByUsername(name);
        return user.getUserLibrary().stream().map(
                comics -> new ComicsResponse(
                comics.getId(),
                comics.getName(),
                comics.getAuthor().getUsername(),
                comics.getPosterPath(),
                comics.getPublishYear(),
                comics.getDescription(),
                comics.getGenres().stream().map(genre -> new GenreDTO(genre.getGenre())).toList()
        )).toList();
    }

    @Override
    public void removeComics(Long id) {
        User user = _userRepo.findByUsername("test"); //Получу из контекста
        Optional<Comics> comics = _comicsRepo.findById(id);
        if(comics.isPresent()){
            Set<User> readers = comics.get().getReaders();
            readers.remove(user);
            _comicsRepo.save(comics.get());
        }
    }

    @Override
    public void addComicsToUserLibrary(String username, Long comicsId) throws ChangeSetPersister.NotFoundException {
        User user = _userRepo.findByUsername(username); //Получу из контекста
        Optional<Comics> comics = _comicsRepo.findById(comicsId);
        if(comics.isEmpty()) throw new ChangeSetPersister.NotFoundException();
        Comics addedComics = comics.get();
        Set<User> readers = addedComics.getReaders();
        readers.add(user);
        _comicsRepo.save(addedComics);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepo.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("user doesn't exist");
        else {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user
                            .getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .toList())
                    .build();
        }
    }
}
