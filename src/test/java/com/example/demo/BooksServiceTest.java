package com.example.demo;

import com.example.demo.model.BookDto;
import com.example.demo.service.BooksService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
public class BooksServiceTest {

    @Autowired
    private BooksService booksService;

    @Test
    @Sql(value = "/BooksServiceTest/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createBookTest() {
        booksService.createBook(new BookDto());
        Assertions.assertThat(booksService.findAllBooks()).hasSize(1);
    }

    @Test
    @Sql(value = "/BooksServiceTest/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql("/BooksServiceTest/insert.sql")
    void findBooksTest() {
        Assertions.assertThat(booksService.findBooks("SpecialBook")).hasSize(1);
    }


    @Test
    @Sql(value = "/BooksServiceTest/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql("/BooksServiceTest/insert.sql")
    void findAllBooksTest() {
        Assertions.assertThat(booksService.findAllBooks()).hasSize(4);
    }

    @Test
    @Sql("/BooksServiceTest/insert.sql")
    void findBookByIdTest() {
        Assertions.assertThat(booksService.findBookById(1)).returns(1L, BookDto::getId).returns("Book1", BookDto::getTitle);
    }
}