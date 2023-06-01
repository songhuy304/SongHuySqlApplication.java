package com.example.songhuysql.entity;


import com.example.songhuysql.Validator.annotation.ValidCategoryId;
import com.example.songhuysql.Validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Category;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data

@Entity
@Table(name = "user")
public class User {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must be less than 50 characters")
    @ValidUsername
    private String username;
    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Password is required")
    private String password;
    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Email must be less than 50 characters")
    private String email;
    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50, message = "Your name must be less than 50 characters")
    @NotBlank(message = "Your name is required")
    private String name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}