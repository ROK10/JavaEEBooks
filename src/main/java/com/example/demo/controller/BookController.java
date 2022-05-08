package com.example.demo.controller;

import com.example.demo.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.BooksService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping
public class BookController {

    @Autowired
    BooksService booksService;


    @GetMapping("/")
    public String bookFormGet(Model model) {
        model.addAttribute("books", booksService.findAllBooks());
        return "books-main";
    }

    //List<BookDto> books = new ArrayList<>();
    @RequestMapping(value = "/createBooks", method = RequestMethod.POST)
    public ResponseEntity<List<BookDto>> saveBook(@RequestBody BookDto bookDto){
        booksService.createBook(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(booksService.findAllBooks());
    }

    @RequestMapping(value = "/searchBooks", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> bookFormFind(@RequestParam("searchField") String search){
        return  ResponseEntity.status(HttpStatus.OK).body(booksService.findBooks(search));
    }

    @RequestMapping(value = "/getBooksList", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.findAllBooks());
    }

    /*@GetMapping("/clearBooks")
    public void clear(){
        books.clear();
    }*/
    @GetMapping("/book/{id}")
    public String showHtmlBook(Model model, @PathVariable(name="id") int id) {
        BookDto book = booksService.findBookById(id);
        if (book == null)
            return "book_does_not_exist";
        model.addAttribute("book", book);
        return "book";
    }
}
