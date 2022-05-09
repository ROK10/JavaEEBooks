package com.example.demo.controller;

import com.example.demo.model.BookDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.BooksService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class BookController {

    @Autowired
    BooksService booksService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    public ResponseEntity<List<BookDto>> bookFormFind(@RequestParam("searchField") final String searchField){
        return  ResponseEntity.status(HttpStatus.OK).body(booksService.findBooks(searchField));
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
    public String showHtmlBook(Model model, @PathVariable(name="id") long id) {
        BookDto book = booksService.findBookById(id);
        if (book == null)
            return "book_does_not_exist";
        model.addAttribute("book", book);
        return "book";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public String saveUser(@RequestParam(name = "login") String login,
                           @RequestParam(name = "password") String password) {
        userRepository.save(User.builder().login(login).password(password).build());
        return "redirect:/login";
    }

    @RequestMapping(value = "/books_wishlist", method = RequestMethod.GET)
    public String showHtml(Model model, Authentication authentication){
        User user = userRepository.findByLogin(authentication.getName()).get();
        List<BookDto> wishList = user.getBooks();
        model.addAttribute("books", wishList);
        return "books_wishlist";
    }

    @RequestMapping(value = "/add_to_wishlist/{id}", method = RequestMethod.GET)
    public String addDeleteWishList(Model model, @PathVariable(name="id") long id, Authentication authentication){
        User user = userRepository.findByLogin(authentication.getName()).get();
        List<BookDto> wishList = user.getBooks();
        if (wishList.stream().anyMatch(book -> book.getId()==id)) {
            wishList = wishList.stream().dropWhile(book -> book.getId()==id).collect(Collectors.toList());
        } else {
            wishList.add(booksService.findBookById(id));
        }
        user.setBooks(wishList);
        userRepository.save(user);
        return "redirect:/";
    }

}
