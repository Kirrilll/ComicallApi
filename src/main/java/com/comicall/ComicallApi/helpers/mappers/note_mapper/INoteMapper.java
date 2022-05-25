package com.comicall.ComicallApi.helpers.mappers.note_mapper;

import com.comicall.ComicallApi.dtos.note.NoteDefaultDto;
import com.comicall.ComicallApi.entities.Note;

import java.util.Collection;
import java.util.List;

public interface INoteMapper {
    //Note toEntity(NoteRequest note);
    NoteDefaultDto toDTO(Note note);

    List<NoteDefaultDto> toDTOs(Collection<Note> notes);
}
