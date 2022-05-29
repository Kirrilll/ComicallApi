package com.comicall.ComicallApi.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.aspectj.weaver.ast.Not;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comics> createdComics;

//    @ManyToMany(mappedBy = "readers")
//    private Set<Comics> userLibrary;

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UsersComics> userLibrary;

    @OneToMany(mappedBy = "noteAuthor")
    private Set<Note> notes;

    public User(){
        roles = new HashSet<>();
        userLibrary = new HashSet<>();
        createdComics = new HashSet<>();
    }

    public User(Long id, String username, String password, Set<Role> roles, Set<Comics> createdComics, Set<UsersComics> userLibrary) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdComics = createdComics;
        this.userLibrary = userLibrary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Comics> getCreatedComics() {
        return createdComics;
    }

    public void setCreatedComics(Set<Comics> createdComics) {
        this.createdComics = createdComics;
    }

    public Set<UsersComics> getUserLibrary() {
        return userLibrary;
    }

    public void setUserLibrary(Set<UsersComics> userLibrary) {
        this.userLibrary = userLibrary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}