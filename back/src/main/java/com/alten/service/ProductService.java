package com.alten.service;

import com.alten.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product, String username);

    Product updateProduct(Long id, Map<String, Object> updates, String username);

    void deleteProduct(Long id, String username);
}
