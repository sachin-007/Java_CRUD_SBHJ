package com.example.nimap.service;

import com.example.nimap.model.CategoryModel;
import com.example.nimap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<CategoryModel> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public CategoryModel getCategoryById(int id) {
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public CategoryModel createCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }



    public CategoryModel updateCategory(int id, CategoryModel category) {
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            CategoryModel existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        } else {
            return null;
        }
    }


    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }


    public boolean existsById(int id) {
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.isPresent();
    }
}
