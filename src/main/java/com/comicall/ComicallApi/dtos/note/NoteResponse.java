package com.comicall.ComicallApi.dtos.note;

public class NoteResponse {
    private Long id;
    private String note;

    public NoteResponse(){}

    public NoteResponse(Long id, String note) {
        this.id = id;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
