package com.example.demo.service;

import com.example.demo.model.BookDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BooksService {

    private BookRepository bookRepository;

    @Transactional
    public BookDto createBook(BookDto book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<BookDto> findBooks(String search){
        return bookRepository.findBookDtoByTitleContainsOrIsbnContains(search, search);
    }

    @Transactional
    public List<BookDto> findAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public BookDto findBookById(long id){
        return bookRepository.findById(id).get();
    }

}