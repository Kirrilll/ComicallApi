package com.comicall.ComicallApi.services.auth;

import com.comicall.ComicallApi.configs.jwt.JwtUtils;
import com.comicall.ComicallApi.dtos.user.JwtResponse;
import com.comicall.ComicallApi.dtos.user.RegisterRequest;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.RoleRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private RoleRepository _roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public Optional<User> registerUser(RegisterRequest signupRequest) {
        if (_userRepository.existsByUsername(signupRequest.getUsername())) {
            return Optional.empty();
        }
        return Optional.of(_userRepository.save(registration(signupRequest)));
    }

    @Override
    public JwtResponse signIn(RegisterRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public User registerAuthor(RegisterRequest registerRequest) {
        User user;
        if(!_userRepository.existsByUsername(registerRequest.getUsername())) user = registration(registerRequest);
        else user = _userRepository.findByUsername(registerRequest.getUsername());
        Set<Role> roles =  user.getRoles();
        roles.add(_roleRepository.findByName("AUTHOR"));
        return _userRepository.save(user);
    }

    private User registration(RegisterRequest registerRequest){
        User user = new User(
                null,
                registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );
        Role reader = _roleRepository.findByName("READER");

        Set<Role> roles = user.getRoles();
        roles.add(reader);
        user.setRoles(roles);
        return user;
    }
}
