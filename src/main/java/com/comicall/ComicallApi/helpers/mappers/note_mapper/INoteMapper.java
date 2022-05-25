package com.comicall.ComicallApi.helpers.mappers.note_mapper;

import com.comicall.ComicallApi.dtos.note.NoteRequest;
import com.comicall.ComicallApi.dtos.note.NoteResponse;
import com.comicall.ComicallApi.entities.Note;

import java.util.Collection;
import java.util.List;

public interface INoteMapper {
    //Note toEntity(NoteRequest note);
    NoteResponse toDTO(Note note);

    List<NoteResponse> toDTOs(Collection<Note> notes);
}
