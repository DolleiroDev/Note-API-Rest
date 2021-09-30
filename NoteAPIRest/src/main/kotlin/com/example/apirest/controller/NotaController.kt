package com.example.apirest.controller

import com.example.apirest.models.Note
import com.example.apirest.repositories.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notes")
class NotaController(private val noteRepository: NoteRepository){

    @PostMapping()
    fun create(@RequestBody note: Note): Note {
        return noteRepository.save(note)
    }
    @GetMapping
    fun read(): List<Note> {
        return noteRepository.findAll().toList()
    }
    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody note: Note) : Note {
        if (noteRepository.existsById(id)) {
            val safeNote = note.copy(id)
            return noteRepository.save(safeNote)
        }
        return Note()
    }
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id)
        }
    }
}
