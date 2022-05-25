package com.comicall.ComicallApi.dtos.note;

public class NoteDefaultDto {
    private Long id;
    private String note;

    public NoteDefaultDto(){}

    public NoteDefaultDto(Long id, String note) {
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
