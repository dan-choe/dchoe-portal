package com.dchoe.portal.controller;

import com.dchoe.portal.model.Note;
import com.dchoe.portal.repositories.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin("*")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/page")
    public ResponseEntity<Page<Note>> getNotesByPage(@RequestParam("page") Integer page, @RequestParam("page-size") Integer pageSize) {
        Sort sortDesc = new Sort(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, pageSize, sortDesc);
        Page<Note> mypage = noteRepository.findAll(pageable);
        return new ResponseEntity<>(mypage, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<List<Note>>(noteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Note getNoteBy_id(@PathVariable("id") String id) {
        return noteRepository.findBy_id(id);
    }

    @PostMapping()
    public ResponseEntity<String> addNote(@RequestBody Note newNote) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();
        newNote.setCreatedAt(dateFormat.format(currentDate));
        newNote.setUpdatedAt(dateFormat.format(currentDate));
        noteRepository.save(newNote);
        return new ResponseEntity<String>("Added a note", HttpStatus.OK);
    }

    @PutMapping()
    public void modifyNoteBy_id(@PathVariable("id") String id, @RequestBody Note note) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();
        note.setUpdatedAt(dateFormat.format(currentDate));
        noteRepository.save(note);
    }

    @DeleteMapping("/{id}")
    public void removNote(@PathVariable("id") String id) {
        noteRepository.delete(noteRepository.findBy_id(id));
    }
}
