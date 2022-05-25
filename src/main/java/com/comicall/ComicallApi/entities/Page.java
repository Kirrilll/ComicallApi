package com.comicall.ComicallApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private int pageNumber;
    private String path;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "comics_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comics comics;

    @OneToMany(mappedBy = "notedPage")
    private Set<Note> notes;

    public Page() {}

    public Page(Long id, int pageNumber, String path, Comics comics, Set<Note> notes) {
        this.id = id;
        this.pageNumber = pageNumber;
        this.path = path;
        this.comics = comics;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", pageNumber=" + pageNumber +
                ", path='" + path + '\'' +
                ", comics=" + comics +
                ", notes=" + notes +
                '}';
    }
}
