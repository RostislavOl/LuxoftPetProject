package com.luxoft.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.GenreDTO;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.repository.GenreRepository;
import com.luxoft.library.service.GenreService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableTransactionManagement
@DisplayName("Проверка контроллера жанров")
class GenreControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreService service;

    @AfterEach
    void shutDown() {
        genreRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("Добавление жанра")
    public void add_newBook() throws Exception {
        /*initial variables*/
        var genreDTO = GenreDTO.builder()
                .genreName("Антология").build();

        /*setting request*/
        var response =
                mockMvc.perform(
                        post("/genre").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        genreDTO)))
                        .andExpect(status().isOk()).andReturn();
        var actual = objectMapper.readValue(response.getResponse().getContentAsString(), GenreDTO.class);

        /*assertions block*/
        assertThat(genreRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DisplayName("Получение жанра")
    public void get_singleBook() throws Exception {
        /*initial variables*/

        var id = genreRepository
                .save(Genre.builder()
                        .id(UUID.randomUUID())
                        .genreName("Сказка").build()).getId();

        /*setting request*/
        var response =
                mockMvc.perform(get("/genre/" + id)).andExpect(status().isOk())
                        .andReturn();

        var actual = objectMapper.readValue(response.getResponse().getContentAsString(), BookDTO.class);
        var expected = genreRepository.findAll().stream().findFirst().get();

        /*assertions block*/
        assertThat(actual.getId()).isEqualTo(id);
        assertThat("Сказка").isEqualTo(expected.getGenreName());
    }

    @Test
    @Transactional
    @DisplayName("Удаление жанра из репозитория")
    public void delete_book() throws Exception {
        /*initial variables*/
        var id = genreRepository
                .save(Genre.builder()
                        .id(UUID.randomUUID())
                        .genreName("Сказка").build()).getId();

        assertEquals(1, genreRepository.findAll().size());

        /*setting request*/
        mockMvc.perform(delete("/genre/" + id)).andExpect(status().isOk())
                .andReturn();

        /*assertions block*/
        assertEquals(0, genreRepository.findAll().size());
    }

    @Test
    @Disabled
    @Transactional
    @DisplayName("Изменение данных жанра")
    public void edit_bookData() throws Exception {
        /*initial variables*/
        var id = service.save(Genre.builder()
                .id(UUID.randomUUID())
                .genreName("Сказка").build()).getId();

        var genreDTO = GenreDTO.builder()
                .genreName("Фантастика").build();

        mockMvc.perform(
                put("/genre/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                genreDTO)))
                .andExpect(status().isOk());

        var actual = genreRepository.findAll().stream().findFirst().get();

        /*assertions block*/
        assertEquals("Фантастика", actual.getGenreName());
    }

}