package com.example.songhuysql.services;

import com.example.songhuysql.entity.Book;
import com.example.songhuysql.repository.IBookRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepository;
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public List<Book> getAllBookss(Integer pageNo,
                                  Integer pageSize,
                                  String sortBy) {

        return bookRepository.findAllBooks(pageNo, pageSize, sortBy);
    }
    public Book getBookId(Long id){
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);

    }
    public void Addbook(Book book){
        bookRepository.save(book);

    }
    public void updateBook(Book book){
        bookRepository.save(book);

    }
    public List<Book> searchBook(String keyword) {
        return bookRepository.searchBook(keyword);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }



}
