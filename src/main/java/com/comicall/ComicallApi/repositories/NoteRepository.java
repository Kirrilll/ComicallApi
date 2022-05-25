package com.comicall.ComicallApi.repositories;

import com.comicall.ComicallApi.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByNoteAuthor_UsernameEqualsAndNotedPage_IdEquals(String username, Long id);

}