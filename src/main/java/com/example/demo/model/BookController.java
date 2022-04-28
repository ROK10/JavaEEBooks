package com.example.demo.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    List<BookDto> books = new ArrayList<>();

    @RequestMapping(value = "/createBooks", method = RequestMethod.POST)
    public ResponseEntity<List<BookDto>> saveBook(@RequestBody final BookDto bookDto){
        books.add(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/searchBooks")
    public List<BookDto> bookFormFind(@RequestParam("searchField") String search){
        ArrayList<BookDto> results = new ArrayList<>();
        for (BookDto temp: books) {
            if (temp.getAuthor().contains(search) || temp.getIsbn().contains(search) || temp.getTitle().contains(search)){
                results.add(temp);
            }
        }
        return results;
    }

    @GetMapping("/getBooksList")
    public List<BookDto> getAllBooks(){
        return books;
    }

    @GetMapping("/clearBooks")
    public void clear(){
        books.clear();
    }
}
