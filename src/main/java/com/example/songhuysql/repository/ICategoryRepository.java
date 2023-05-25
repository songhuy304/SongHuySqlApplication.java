package com.example.songhuysql.repository;

import com.example.songhuysql.entity.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.songhuysql.entity.Book;
@Repository
public interface ICategoryRepository extends JpaRepository<category, Long> {
}
