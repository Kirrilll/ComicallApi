package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.note.NoteCreatingDto;
import com.comicall.ComicallApi.dtos.note.NoteDefaultDto;
import com.comicall.ComicallApi.entities.Note;
import com.comicall.ComicallApi.services.comics.IComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comics")
@CrossOrigin(origins = "*")
public class ComicsController {

    @Autowired
    private IComicsService _pageService;

    @PostMapping("/note/create")
    public ResponseEntity<Note> createNote(@RequestBody NoteCreatingDto noteCreatingDto){
        return ResponseEntity.ok().body(_pageService.addNoteToPage(noteCreatingDto.getPageId(), noteCreatingDto.getNote()));
    }

    @PatchMapping("/note/update")
    public ResponseEntity<Note> updateNote(@RequestBody NoteDefaultDto noteDefaultDto){
        return ResponseEntity.ok().body(_pageService.changeNote(noteDefaultDto.getId(), noteDefaultDto.getNote()));
    }

    @DeleteMapping("/note/delete")
    public ResponseEntity<MessageDTO> deleteNote(@RequestParam Long noteId){
        _pageService.deleteNote(noteId);
        return ResponseEntity.ok().body(new MessageDTO("note deleted"));
    }

    @PatchMapping("/setBookmark")
    public ResponseEntity<MessageDTO> setBookmark(
            @RequestParam int pageNumber,
            @RequestParam Long comicsId){
        try {
            return ResponseEntity.ok().body(new MessageDTO(_pageService.setBookmark(pageNumber, comicsId) + ""));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO("not found"));
        }

    }

    @GetMapping("/read")
    public ResponseEntity<?> readComics(@RequestParam Long comicsId){
        try {
            return ResponseEntity.ok().body(_pageService.getComicsPages(comicsId));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageDTO("can't find comics with that id"));
        }
    }

}
