package com.example.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void clear() throws Exception {
        mockMvc.perform(get("/clear"));
    }

    @Test
    public void bookCreationTest() throws Exception {
        BookDto book = new BookDto("book1", "1234", "Glybach");
        mockMvc.perform(post("/createBooks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Lists.newArrayList(book))));
    }

    @Test
    void bookListGettingTest() throws Exception {
        List<BookDto> list = new ArrayList<>();
        BookDto book = new BookDto("book1", "1333", "Diach");
        list.add(book);
        mockMvc.perform(post("/createBooks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        mockMvc.perform(get("/getBooksList")).andExpect(status().isOk()).andExpect(
                content().json(objectMapper.writeValueAsString(list)));

    }

    @Test
    void bookSearchingTest() throws Exception {
        List<BookDto> list = new ArrayList<>();
        BookDto book1 = new BookDto("book1", "1333", "Diach");;
        BookDto book2 = new BookDto("book2", "1234", "Glybach");
        list.add(book1);
        mockMvc.perform(post("/createBooks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/createBooks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book2)))
                .andExpect(status().isOk());
        mockMvc.perform(get("/searchBooks")
                        .param("searchField","book1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}