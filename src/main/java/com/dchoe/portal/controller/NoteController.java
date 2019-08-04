package com.dchoe.portal.controller;

import com.dchoe.portal.model.Note;
import com.dchoe.portal.repositories.NoteRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin("*")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/page")
    public ResponseEntity<Page<Note>> getNotesByPage(@RequestParam("page") Integer page, @RequestParam("page-size") Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return new ResponseEntity<Page<Note>>(noteRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<List<Note>>(noteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Note getNoteBy_id(@PathVariable("id") ObjectId id) {
        return noteRepository.findBy_id(id);
    }

    @PostMapping()
    public ResponseEntity<String> addNote(@RequestBody Note newNote) {
        noteRepository.save(newNote);
        return new ResponseEntity<String>("Added Response", HttpStatus.OK);
    }

    @PutMapping()
    public void modifyNoteBy_id(@PathVariable("id") ObjectId id, @RequestBody Note note) {
        noteRepository.save(note);
    }

    @DeleteMapping("/{id}")
    public void removNote(@PathVariable("id") ObjectId id) {
        noteRepository.delete(noteRepository.findBy_id(id));
    }
}
