package com.example.songhuysql.Controller;
import com.example.songhuysql.entity.Book;
import com.example.songhuysql.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;

import com.example.songhuysql.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.getAllcategory());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,Model model){
       if(bindingResult.hasErrors()){
           model.addAttribute("categories" , categoryService.getAllcategory());
           return "book/add";
       }
        bookService.Addbook(book);
        return "redirect:/books";

    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookId(id);
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.getAllcategory());
        return "book/edit";
    }
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        Book existingBook = bookService.getBookId(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        existingBook.setCategory(book.getCategory());
        existingBook.setPrice(book.getPrice());
        // Update other properties as needed

        bookService.updateBook(existingBook);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Book> results = bookService.searchBooks(keyword);
        model.addAttribute("books", results);
        model.addAttribute("keyword", keyword);
        return "book/search";
    }



}
