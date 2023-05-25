package com.example.songhuysql.Controller;

import com.example.songhuysql.entity.Book;
import com.example.songhuysql.entity.category;
import com.example.songhuysql.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllcategory(Model model){
        List<category> categories = categoryService.getAllcategory();
        model.addAttribute("categories",categories);
        return "category/list";
    }
    @GetMapping("/add")
    public String addCateForm(Model model){
        model.addAttribute("category",new category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCate(@ModelAttribute("category") category cate){
        categoryService.Addcategory(cate);
        return "redirect:/categories";

    }
    @GetMapping("/edit/{id}")
    public String editcateForm(@PathVariable("id") Long id, Model model ){
            category category = categoryService.getcategoryId(id);
            model.addAttribute("category" , category);
            return "category/edit";

    }
    @PostMapping("/edit/{id}")
    public String editcate(@PathVariable("id") Long id, @ModelAttribute("category") category cate) {
        category existingBook = categoryService.getcategoryId(id);
        existingBook.setName(cate.getName());

        // Update other properties as needed

        categoryService.updatecate(existingBook);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deletecate(@PathVariable("id") Long id ){
        category deletecate1 = categoryService.getcategoryId(id);
        categoryService.deleteBook(deletecate1.getId());
        return "redirect:/categories";
    }

}
