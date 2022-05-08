package com.example.demo.repository;

import com.example.demo.model.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookDto, Long> {
    List<BookDto> findBookDtoByTitleContainsOrIsbnContains(String search1, String search2);
}