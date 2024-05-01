package com.example.nimap.controller;

import com.example.nimap.model.CategoryModel;
import com.example.nimap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryModel> categoryPage = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(categoryPage.getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {
        CategoryModel category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return new ResponseEntity<>("Category with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryModel category) {
        if (category == null || category.getName() == null || category.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Empty category name");
        }

        CategoryModel createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable int id, @RequestBody CategoryModel category) {
        CategoryModel updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return new ResponseEntity<>("Category with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        if (categoryService.existsById(id)) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Category " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
