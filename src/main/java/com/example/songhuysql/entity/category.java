package com.example.songhuysql.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "category")
public class category {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Book> books;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        category category = (category) o;
        return getId() != null && Objects.equals(getId(),
                category.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
