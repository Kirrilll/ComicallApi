package com.comicall.ComicallApi.helpers;

import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.User;

import java.util.HashSet;
import java.util.Set;

public class ComicsBuilder implements IBuilder<Comics>{

    Long id = null;
    String name;
    String description;
    int publishYear;
    String  posterPath;
    User author;
    Set<Genre> genres = new HashSet<>();
    Set<User> readers = new HashSet<>();

    public ComicsBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public ComicsBuilder setName(String name){
        this.name = name;
        return this;
    }

    public ComicsBuilder setPublishYear(int year){
        this.publishYear = year;
        return  this;
    }

    public ComicsBuilder setPosterPath(String posterPath){
        this.posterPath = posterPath;
        return this;
    }

    public ComicsBuilder setAuthor(User author){
        this.author =  author;
        return this;
    }

    public ComicsBuilder setGenres(Set<Genre> genres){
        this.genres.addAll(genres);
        return this;
    }

    public ComicsBuilder setReaders(Set<User> readers){
        this.readers.addAll(readers);
        return this;
    }

    @Override
    public Comics build() {
        return new Comics(id, name, description, publishYear, posterPath, author, genres, readers);
    }
}
