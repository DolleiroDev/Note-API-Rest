package com.example.ceepws.controller

import com.example.ceepws.models.Note
import com.example.ceepws.repositories.NoteRespository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notes")
class NotaController {

    @Autowired
    lateinit var noteRepository: NoteRespository

    @PostMapping()
    fun create(@RequestBody note: Note): Note {
        return noteRepository.save(note)
    }
    @GetMapping
    fun read(): List<Note> {
        return noteRepository.findAll().toList()
    }
}
