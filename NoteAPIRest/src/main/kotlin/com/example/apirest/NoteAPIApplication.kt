package com.example.apirest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NoteAPIApplication

fun main(args: Array<String>) {
	runApplication<NoteAPIApplication>(*args)
}
