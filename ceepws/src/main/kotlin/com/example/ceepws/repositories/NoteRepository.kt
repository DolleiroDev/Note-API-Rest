package com.example.ceepws.repositories

import com.example.ceepws.models.Note
import org.springframework.data.repository.CrudRepository

interface NoteRespository: CrudRepository<Note, Long> {

}