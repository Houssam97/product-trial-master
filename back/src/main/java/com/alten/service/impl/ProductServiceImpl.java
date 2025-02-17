package com.alten.service.impl;

import com.alten.models.Product;
import com.alten.repository.ProductRepository;
import com.alten.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product, String username) {
        if (!"admin@admin.com".equals(username)) {
            throw new RuntimeException("Only admin can add products");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Map<String, Object> updates, String username) {
        if (!"admin@admin.com".equals(username)) {
            throw new RuntimeException("Only admin can update products");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Product.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, product, value);
            }
        });

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id, String username) {
        if (!"admin@admin.com".equals(username)) {
            throw new RuntimeException("Only admin can delete products");
        }
        productRepository.deleteById(id);
    }
}
