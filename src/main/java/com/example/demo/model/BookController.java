package com.example.demo.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookDto bookDto = new BookDto();

    List<BookDto> books = new ArrayList<>();

    @GetMapping("/create")
    public String bookFormGet(Model model){
        model.addAttribute("bookDto",bookDto);
        return "create-book";
    }

    @PostMapping("/create")
    public String saveBook(BookDto bookDto, Model model){
        books.add(bookDto);
        return "redirect:/book/view-books";
    }
    @GetMapping("/view-books")
    public String viewBooks(Model model){
        model.addAttribute("books",books);
        return "view-books";
    }
}
