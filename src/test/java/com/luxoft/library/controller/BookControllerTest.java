package com.luxoft.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.repository.AuthorRepository;
import com.luxoft.library.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @AfterEach
    void shutDown(){
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    @DisplayName("Добавление книги")
    public void add_newBook() throws Exception {
        /*initial variables*/
        var author = Author.builder()
                                    .id(UUID.randomUUID())
                                    .fullName("А.С. Пушкин")
                                    .build();

        var bookDTO = NewBookDTO.builder()
                                            .name("test book")
                                            .author(Collections.singletonList(author))
                                            .genre(Genre.ANTHOLOGY).build();
        /*setting request*/
        var response =
                mockMvc.perform(
                        post("/book").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        bookDTO)))
                        .andExpect(status().isOk()).andReturn();
        var actual = objectMapper.readValue(response.getResponse().getContentAsString(), BookDTO.class);

        /*assertions block*/
        assertThat(bookRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Получение книги")
    public void get_singleBook() throws Exception {
        /*initial variables*/
        var author = Author.builder()
                .id(UUID.randomUUID())
                .fullName("А.С. Пушкин")
                .build();

        //authorRepository.saveAllAndFlush(author);

        var id = bookRepository
                .save(Book.builder()
                        .name("Сказка о рыбаке и рыбке")
                        //.author(Collections.singletonList(author))
                        .comment(Collections.emptyList())
                        .genre(Genre.FAIRYTALE).build()).getId();

        var response =
                mockMvc.perform(get("/autopark/" + id)).andExpect(status().isOk())
                        .andReturn();

        var actual = objectMapper.readValue(response.getResponse().getContentAsString(), BookDTO.class);
        var expected = bookRepository.findAll().get(0);

        /*assertions block*/
        assertThat(actual.getId()).isEqualTo(id);
    }

}