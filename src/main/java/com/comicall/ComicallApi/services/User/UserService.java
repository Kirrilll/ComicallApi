package com.comicall.ComicallApi.services.User;

import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.repositories.RoleRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return _userRepo.save(user);
    }

    //Создать для этого отдельный service
    @Override
    public Role saveRole(Role role) {
        return _roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = _userRepo.findByUsername(username);
        Role role = _roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return _userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return _userRepo.findAll();
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
