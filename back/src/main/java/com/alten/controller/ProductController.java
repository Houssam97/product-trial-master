package com.alten.controller;

import com.alten.models.Product;
import com.alten.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(productService.createProduct(product, userDetails.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates, @AuthenticationPrincipal UserDetails userDetails) {

        Product updatedProduct = productService.updateProduct(id, updates, userDetails.getUsername());
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        productService.deleteProduct(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
