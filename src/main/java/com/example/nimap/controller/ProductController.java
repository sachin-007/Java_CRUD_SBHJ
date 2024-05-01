package com.example.nimap.controller;

import com.example.nimap.model.CategoryModel;
import com.example.nimap.model.ProductModel;
import com.example.nimap.service.CategoryService;
import com.example.nimap.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductModel> productPage = productService.getAllProducts(pageable);
        return ResponseEntity.ok(productPage.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        ProductModel product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductModel product) {
          if (product == null || product.getTitle() == null || product.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Empty product name");
        }

        ProductModel createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody ProductModel product) {
        ProductModel updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);

        }
    }

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable int id) {
    if (productService.existsById(id)) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
    } else {
        return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
    }
}

}
