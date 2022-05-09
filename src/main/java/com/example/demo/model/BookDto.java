package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;

    @ManyToMany(mappedBy = "books")
    List<User> users;

}
