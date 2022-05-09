package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Login cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "Login must have only Latin letters and numbers")
    private String login;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^.{8,20}", message = "Password has to be from 8 to 20 characters")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "bookID"))
    List<BookDto> books;


}