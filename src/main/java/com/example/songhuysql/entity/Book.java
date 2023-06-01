package com.example.songhuysql.entity;

import com.example.songhuysql.Validator.annotation.ValidCategoryId;
import com.example.songhuysql.Validator.annotation.ValidUsername;
import jakarta.persistence.*;

import com.example.songhuysql.Validator.annotation.ValidUserId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Category;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Data
@Entity
@Table(name = "book")
public class Book {
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public com.example.songhuysql.entity.category getCategory() {
        return category;
    }

    public void setCategory(com.example.songhuysql.entity.category category) {
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "title")
    @NotEmpty(message = "Title must not be Emty")
    @Size(max = 50 , min = 1,message = "Tittle must be less than 50 cha")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    @NotNull(message = "price iss require")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id" )
    @ValidCategoryId
    private category category;
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @ValidUserId
    private User user;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(),
                book.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
