package com.comicall.ComicallApi.entities;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UsersComics {

    @EmbeddedId
    private UserComicsKey id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comics_id")
    @MapsId("comicsId")
    private Comics comics;

    private boolean isRead;
    private int markedPage;

    public UsersComics(){}
    public UsersComics(UserComicsKey id, User user, Comics comics, boolean isRead, int markedPage) {
        this.id = id;
        this.user = user;
        this.comics = comics;
        this.isRead = isRead;
        this.markedPage = markedPage;
    }

    public UserComicsKey getId() {
        return id;
    }

    public void setId(UserComicsKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }

    public int getMarkedPage() {
        return markedPage;
    }

    public void setMarkedPage(int markedPage) {
        this.markedPage = markedPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersComics that = (UsersComics) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
