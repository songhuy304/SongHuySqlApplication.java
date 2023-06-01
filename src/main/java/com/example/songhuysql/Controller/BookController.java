package com.example.songhuysql.Controller;
import com.example.songhuysql.daos.Item;
import com.example.songhuysql.entity.Book;
import com.example.songhuysql.services.CartService;
import com.example.songhuysql.services.CategoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    @Autowired
    private CartService cartService;

//    @GetMapping
//    public String showAllBooks(Model model){
//        List<Book> books = bookService.getAllBooks();
//        model.addAttribute("books",books);
//        return "book/list";
//    }
    @GetMapping
    public String showAllBookss(
            @NotNull Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("books", bookService.getAllBookss(pageNo,
                pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);



        model.addAttribute("categories",
                categoryService.getAllcategory());
        model.addAttribute("totalPages",
                bookService.getAllBookss(pageNo, pageSize, sortBy).size() / pageSize);
        return "book/list";
    }
    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/books";
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
    @GetMapping("/search")
    public String searchBook(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("books", bookService.searchBook(keyword));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                bookService
                        .getAllBookss(pageNo, pageSize, sortBy)
                        .size() / pageSize);
        model.addAttribute("categories",
                categoryService.getAllcategory());
        return "book/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        if(bookService.getBookId(id) != null){
            bookService.deleteBook(id);

        }
        return "redirect:/books";
    }





}
