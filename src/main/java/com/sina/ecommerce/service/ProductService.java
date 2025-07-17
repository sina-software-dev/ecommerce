package com.sina.ecommerce.service;

import com.sina.ecommerce.model.Product;
import com.sina.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
