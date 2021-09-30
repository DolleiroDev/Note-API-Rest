package com.example.apirest.controller

import com.example.apirest.models.Note
import com.example.apirest.repositories.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notes")
class NotaController {

    @Autowired
    lateinit var noteRepository: NoteRepository

    @PostMapping()
    fun create(@RequestBody note: Note): Note {
        return noteRepository.save(note)
    }
    @GetMapping
    fun read(): List<Note> {
        return noteRepository.findAll().toList()
    }
}
