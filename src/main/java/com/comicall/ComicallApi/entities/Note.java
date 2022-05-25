package com.comicall.ComicallApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "noted_page_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Page notedPage;

    private String note;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "note_author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User noteAuthor;

    public Note(){}

    public Note(Long id, Page notedPage, String note, User noteAuthor) {
        this.id = id;
        this.notedPage = notedPage;
        this.note = note;
        this.noteAuthor = noteAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page getNotedPage() {
        return notedPage;
    }

    public void setNotedPage(Page notedPage) {
        this.notedPage = notedPage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getNoteAuthor() {
        return noteAuthor;
    }

    public void setNoteAuthor(User noteAuthor) {
        this.noteAuthor = noteAuthor;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", notedPage=" + notedPage +
                ", note='" + note + '\'' +
                ", noteAuthor=" + noteAuthor +
                '}';
    }
}