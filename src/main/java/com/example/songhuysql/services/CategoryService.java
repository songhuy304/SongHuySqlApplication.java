package com.example.songhuysql.services;

import com.example.songhuysql.entity.Book;
import com.example.songhuysql.entity.category;
import com.example.songhuysql.repository.IBookRepository;
import com.example.songhuysql.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    public List<category> getAllcategory(){
        return categoryRepository.findAll();
    }
    public category getcategoryId(Long id){
        Optional<category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();

        }else {
            throw new RuntimeException("Category is not funot");
        }


    }

    public void Addcategory(category cate){
        categoryRepository.save(cate);

    }
    public void updatecate(category cate){
        categoryRepository.save(cate);

    }
    public category saveCategory(category categorys){
        return categoryRepository.save(categorys);
    }

    public void deleteBook(Long id){
        categoryRepository.deleteById(id);
    }
}
