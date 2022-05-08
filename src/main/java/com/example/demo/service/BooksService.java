package com.example.demo.service;

import com.example.demo.model.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final EntityManager entityManager;

    @Transactional
    public BookDto createBook(BookDto book) {
        return entityManager.merge(book);
    }

    @Transactional
    public List<BookDto> findBooks(String search){
        return entityManager.createQuery("SELECT b FROM BookDto b WHERE b.author LIKE :query OR b.title LIKE :query OR b.isbn LIKE :query", BookDto.class)
                .setParameter("query", '%' + search + '%')
                .getResultList();
    }

    @Transactional
    public List<BookDto> findAllBooks(){
        List<BookDto> allBooksList =  entityManager.createQuery("SELECT b FROM BookDto b", BookDto.class).getResultList();
        return allBooksList;
    }

    @Transactional
    public BookDto findBookById(long id){
        return entityManager.find(BookDto.class, id);
    }

}