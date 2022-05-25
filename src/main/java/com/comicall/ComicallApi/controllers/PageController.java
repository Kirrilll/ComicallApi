package com.comicall.ComicallApi.controllers;

import com.comicall.ComicallApi.dtos.MessageDTO;
import com.comicall.ComicallApi.dtos.note.NoteCreatingDto;
import com.comicall.ComicallApi.dtos.note.NoteDefaultDto;
import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Note;
import com.comicall.ComicallApi.services.page.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/page")
@CrossOrigin(origins = "*")
public class PageController {

    @Autowired
    private IPageService _pageService;

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody NoteCreatingDto noteCreatingDto){
        return ResponseEntity.ok().body(_pageService.addNoteToPage(noteCreatingDto.getPageId(), noteCreatingDto.getNote()));
    }

    @PatchMapping("/update")
    public ResponseEntity<Note> updateNote(@RequestBody NoteDefaultDto noteDefaultDto){
        return ResponseEntity.ok().body(_pageService.changeNote(noteDefaultDto.getId(), noteDefaultDto.getNote()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageDTO> deleteNote(@RequestParam Long noteId){
        _pageService.deleteNote(noteId);
        return ResponseEntity.ok().body(new MessageDTO("note deleted"));
    }


    @GetMapping("/read")
    public ResponseEntity<List<PageResponse>> readComics(@RequestParam Long comicsId){
        try {
            return ResponseEntity.ok().body(_pageService.getComicsPages(comicsId));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

}
