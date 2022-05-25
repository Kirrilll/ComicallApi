package com.comicall.ComicallApi.helpers.mappers.note_mapper;

import com.comicall.ComicallApi.dtos.note.NoteRequest;
import com.comicall.ComicallApi.dtos.note.NoteResponse;
import com.comicall.ComicallApi.entities.Note;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class NoteMapper implements INoteMapper{
//    @Override
//    public Note toEntity(NoteRequest note) {
//        return new Note(null, );
//    }

    @Override
    public NoteResponse toDTO(Note note) {
        return new NoteResponse(note.getId(), note.getNote());
    }

    @Override
    public List<NoteResponse> toDTOs(Collection<Note> notes) {
        return notes.stream().map(this::toDTO).toList();
    }
}
