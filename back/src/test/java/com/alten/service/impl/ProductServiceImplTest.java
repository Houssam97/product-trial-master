package com.alten.service.impl;

import com.alten.models.Product;
import com.alten.repository.ProductRepository;
import com.alten.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct_Admin() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1200.00);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product, "admin@admin.com");

        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getName());
        assertEquals(1200.00, createdProduct.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test
    void testCreateProduct_NonAdmin() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1200.00);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.createProduct(product, "user@example.com");
        });

        assertEquals("Only admin can add products", exception.getMessage());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }
}
