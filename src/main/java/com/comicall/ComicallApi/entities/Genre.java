package com.comicall.ComicallApi.entities;

import com.comicall.ComicallApi.entities.enums.EGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EGenre genre;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Set<Comics> comics;

    public Genre(){
        this.comics = new HashSet<>();
    }

    public Genre(Long id, EGenre genre, Set<Comics> comics) {
        this.id = id;
        this.genre = genre;
        this.comics = comics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EGenre getGenre() {
        return genre;
    }

    public void setGenre(EGenre genre) {
        this.genre = genre;
    }

    public Set<Comics> getComics() {
        return comics;
    }

    public void setComics(Set<Comics> comics) {
        this.comics = comics;
    }
}
