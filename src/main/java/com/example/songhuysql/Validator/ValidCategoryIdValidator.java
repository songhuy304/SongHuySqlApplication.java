package com.example.songhuysql.Validator;

import com.example.songhuysql.entity.category;
import com.example.songhuysql.Validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator  implements ConstraintValidator<ValidCategoryId, category> {
    @Override
    public boolean isValid(category category, ConstraintValidatorContext context){
        return category != null && category.getId() != null;
    }
}
