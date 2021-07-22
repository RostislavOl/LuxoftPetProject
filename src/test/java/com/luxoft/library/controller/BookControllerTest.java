package com.luxoft.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.repository.AuthorRepository;
import com.luxoft.library.repository.BookRepository;
import com.luxoft.library.repository.GenreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Проверка книжного контроллера")
class BookControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @AfterEach
    void shutDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("Добавление книги")
    public void add_newBook() throws Exception {
        /*initial variables*/
        var author = Author.builder()
                .id(UUID.randomUUID())
                .fullName("А.С. Пушкин")
                .build();

        authorRepository.saveAndFlush(author);

        var genre = Genre.builder()
                                .id(UUID.randomUUID())
                                .genreName("Антология").build();

        genreRepository.saveAndFlush(genre);

        var bookDTO = NewBookDTO.builder()
                .name("test book")
                .author(Collections.singletonList(author.getId()))
                .comment(Collections.emptyList())
                .genre(genre).build();
        /*setting request*/
        var response =
                mockMvc.perform(
                        post("/book").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        bookDTO)))
                        .andExpect(status().isOk()).andReturn();
        var actual = objectMapper.readValue(response.getResponse().getContentAsString(), BookDTO.class);

        /*assertions block*/
        assertThat(bookRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Получение книги")
    public void get_singleBook() throws Exception {
        /*initial variables*/

        var genre = Genre.builder()
                .id(UUID.randomUUID())
                .genreName("Сказка").build();

        var id = bookRepository
                .save(Book.builder()
                        .name("Сказка о рыбаке и рыбке")
                        .comment(Collections.emptyList())
                        .genre(genre).build()).getId();

        /*setting request*/
        var response =
                mockMvc.perform(get("/book/" + id)).andExpect(status().isOk())
                        .andReturn();

        var actual = objectMapper.readValue(response.getResponse().getContentAsString(), BookDTO.class);
        var expected = bookRepository.findAll().get(0);

        /*assertions block*/
        assertThat(actual.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Удаление книги из репозитория")
    public void delete_book() throws Exception {
        /*initial variables*/
        var genre = Genre.builder()
                .id(UUID.randomUUID())
                .genreName("Сказка").build();

        var id = bookRepository
                .save(Book.builder()
                        .name("Сказка о рыбаке и рыбке")
                        .comment(Collections.emptyList())
                        .genre(genre).build()).getId();

        assertEquals(1, bookRepository.findAll().size());

        /*setting request*/
        mockMvc.perform(delete("/book/" + id)).andExpect(status().isOk())
                .andReturn();

        /*assertions block*/
        assertEquals(0, bookRepository.findAll().size());
    }

    /*@Test
    @DisplayName("Изменение данных книги")
    public void edit_bookData() throws Exception {
        /*initial variables
        var id = bookRepository
                .save(Book.builder()
                        .name("Сказка о рыбаке и рыбке")
                        .comment(Collections.emptyList())
                        .genre(Genre.FAIRYTALE).build()).getId();

        var bookDTO = NewBookDTO.builder()
                .name("Мечтают ли андроиды об электроовцах")
                .comment(Collections.emptyList())
                .genre(Genre.SCIENCEFICTION).build();

        mockMvc.perform(
                put("/book/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                bookDTO)))
                .andExpect(status().isOk());

        var actual = bookRepository.findAll().get(0);

        /*assertions block
        assertEquals(Genre.SCIENCEFICTION, actual.getGenre());
        assertEquals("Мечтают ли андроиды об электроовцах", actual.getName());
    }*/
}