package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

    @NotEmpty(message = "Title is null")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "ISBN is null")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN has bad format")
    @Column(name = "isbn")
    private String isbn;

    @NotEmpty(message = "Author is null")
    @Column(name = "author")
    private String author;

    @ManyToMany(mappedBy = "books")
    List<User> users;

}
