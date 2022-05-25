package com.comicall.ComicallApi.dtos.note;

public class NoteCreatingDto {
    private String note;
    private Long pageId;

    public NoteCreatingDto(){}

    public NoteCreatingDto(String note, Long pageId) {
        this.note = note;
        this.pageId = pageId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }
}
