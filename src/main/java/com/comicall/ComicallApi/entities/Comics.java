package com.comicall.ComicallApi.entities;

import com.comicall.ComicallApi.helpers.ComicsBuilder;
import net.minidev.json.annotate.JsonIgnore;
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

    //public Set<Page> pages;

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

    @ManyToMany()
    @JoinTable(
            name = "user_library",
            joinColumns = @JoinColumn(name = "comics_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> readers;

    public  Comics(){
        this.id = null;
        this.genres = new HashSet<>();
        this.readers = new HashSet<>();
    }

    public Comics(Long id, String name, String description, int publishYear, String posterPath, User author, Set<Genre> genres, Set<User> readers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publishYear = publishYear;
        this.posterPath = posterPath;
        this.author = author;
        this.genres = genres;
        this.readers = readers;
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

    public Set<User> getReaders() {
        return readers;
    }

    public void setReaders(Set<User> readers) {
        this.readers = readers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ComicsBuilder builder(){
        return new ComicsBuilder();
    }

}