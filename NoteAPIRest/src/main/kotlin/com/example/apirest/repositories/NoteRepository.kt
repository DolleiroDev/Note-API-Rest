package com.example.apirest.repositories

import com.example.apirest.models.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository: CrudRepository<Note, Long> {

}