package com.comicall.ComicallApi.dtos.page;

import com.comicall.ComicallApi.dtos.note.NoteResponse;

import java.util.List;

public class PageResponse {
    private Long id;
    private String imagePath;
    private List<NoteResponse> notes;

    public PageResponse(){}

    public PageResponse(Long id, String imagePath, List<NoteResponse> notes) {
        this.id = id;
        this.imagePath = imagePath;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<NoteResponse> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteResponse> notes) {
        this.notes = notes;
    }
}
