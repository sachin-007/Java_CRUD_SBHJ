package com.example.nimap.service;

import com.example.nimap.model.CategoryModel;
import com.example.nimap.model.ProductModel;
import com.example.nimap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductModel> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public ProductModel getProductById(int id) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel updateProduct(int id, ProductModel product) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductModel existingProduct = optionalProduct.get();


            if (product.getTitle() != null) {
                existingProduct.setTitle(product.getTitle());
            }


            if (product.getDescription() != null) {
                existingProduct.setDescription(product.getDescription());
            }


            if (product.getCategory() != null) {
                existingProduct.setCategory(product.getCategory());
            }


            if (product.getOurPrice() != 0.0) {
                existingProduct.setOurPrice(product.getOurPrice());
            }


            if (product.getStarRating() != 0.0) {
                existingProduct.setStarRating(product.getStarRating());
            }


            if (product.getStock() != 0) {
                existingProduct.setStock(product.getStock());
            }

            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }


    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        Optional<ProductModel> optionalCategory = productRepository.findById(id);
        return optionalCategory.isPresent();
    }
}
