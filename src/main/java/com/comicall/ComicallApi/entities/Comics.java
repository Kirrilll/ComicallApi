package com.comicall.ComicallApi.entities;

import com.comicall.ComicallApi.helpers.ComicsBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "comics")
public class Comics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    private int publishYear;
    private String posterPath;

    @OneToMany(mappedBy = "comics")
    private Set<Page> pages;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToMany
    @JoinTable(
            name = "comics_genres",
            joinColumns = @JoinColumn(name = "comics_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Genre> genres;

    @OneToMany(mappedBy = "comics")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UsersComics> readers;

    private boolean isReady;

    public  Comics(){
        this.id = null;
        isReady = false;
        this.genres = new HashSet<>();
        this.readers = new HashSet<>();
        this.pages = new HashSet<>();
    }

    public Comics(Long id, String name, String description, int publishYear, String posterPath, User author, Set<Genre> genres, Set<UsersComics> readers, Set<Page> pages, boolean isReady) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publishYear = publishYear;
        this.posterPath = posterPath;
        this.author = author;
        this.genres = genres;
        this.readers = readers;
        this.pages = pages;
        this.isReady = isReady;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comics comics = (Comics) o;
        return id != null && Objects.equals(id, comics.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public Set<UsersComics> getReaders() {
        return readers;
    }

    public void setReaders(Set<UsersComics> readers) {
        this.readers = readers;
    }

    public static ComicsBuilder builder(){
        return new ComicsBuilder();
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {
        isReady = ready;
    }
}