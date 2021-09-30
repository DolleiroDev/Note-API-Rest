package com.example.apirest

import com.example.apirest.models.Note
import com.example.apirest.repositories.NoteRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class NoteAPIRestTests {

	@Autowired
	lateinit var mockMvc: MockMvc

	@Autowired
	lateinit var noteRepository: NoteRepository

	@Test
	fun testReadFunction() {
		val note = noteRepository.save(Note(title = "Teste", description = "Testando"))
		mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(MockMvcResultHandlers.print())
	}
	@Test
	fun testGetByTitleFunction() {
		val note = noteRepository.save(Note(title = "WHAT IS HAPPENING BRO", description = "Testando"))
		mockMvc.perform(MockMvcRequestBuilders.get("/notes/${note.title}"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print())
	}

	@Test
	fun testCreateFunction() {
		val note = Note(title = "Teste", description = "Testando")
		val json = ObjectMapper().writeValueAsString(note)
		noteRepository.deleteAll()
		mockMvc.perform(MockMvcRequestBuilders.post("/notes")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(MockMvcResultMatchers.status().isCreated)
			.andDo(MockMvcResultHandlers.print())

		Assertions.assertFalse(noteRepository.findAll().toList().isEmpty())
	}
	@Test
	fun testUpdateFunction() {
		val note = noteRepository.save(Note(title = "Teste", description = "Testando"))
			.copy(title = "Titulo Mudado")
		val json = ObjectMapper().writeValueAsString(note)
		mockMvc.perform(MockMvcRequestBuilders.put("/notes/${note.id}")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(MockMvcResultHandlers.print())

		val findByIdTest = noteRepository.findById(note.id)
		Assertions.assertTrue(findByIdTest.isPresent)
		Assertions.assertEquals(note.title, findByIdTest.get().title)
	}
	@Test
	fun testDeleteFunction() {
		val note = noteRepository.save(Note(title = "Teste", description = "Testando"))
		mockMvc.perform(MockMvcRequestBuilders.delete("/notes/${note.id}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(MockMvcResultHandlers.print())
		Assertions.assertFalse(noteRepository.findById(note.id).isPresent)
	}

}
