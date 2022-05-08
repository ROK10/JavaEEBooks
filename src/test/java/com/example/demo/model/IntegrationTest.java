/*
package com.example.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    void setPort(int port){
        RestAssured.port = port;
    }

    @BeforeEach
    public void clear() {
        RestAssured.when().get("/clear");
    }

    @Test
    public void bookCreationTest() {
        BookDto book = new BookDto("book1", "1234", "Mih");
        List<BookDto> books = Collections.singletonList(book);
        List<BookDto> createdBooks = RestAssured
                .given()
                    .contentType("application/json")
                    .body(book)
                .when()
                    .post("/createBooks")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath()
                    .getList("$", BookDto.class);
        assertEquals(createdBooks, books);
    }

    @Test
    public void bookGettingTest() {
        BookDto book = new BookDto("book1", "1234", "Mih");
        List<BookDto> books = Collections.singletonList(book);
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(book)
                .when()
                    .post("/createBooks");
        List<BookDto> createdBooks = RestAssured
                .when()
                    .get("/getBooksList")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath()
                    .getList("$", BookDto.class);
        assertEquals(createdBooks, books);
    }

    @Test
    public void bookSearchingTest() {
        BookDto book1 = new BookDto("book1", "1234", "Glybach");
        BookDto book2 = new BookDto("book2", "4321", "Kashpir");
        BookDto book3 = new BookDto("book3", "1333", "Diach");
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(book1)
                .when()
                    .post("/createBooks");
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(book2)
                .when()
                .   post("/createBooks");
        RestAssured
                .given()
                .contentType("application/json")
                .body(book3)
                .when()
                .   post("/createBooks");
        List<BookDto> books = RestAssured
                .given()
                    .param("searchField","book3")
                .when()
                    .get("/searchBooks")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath()
                    .getList("$", BookDto.class);
        assertEquals(Collections.singletonList(book3), books);
    }
}*/
