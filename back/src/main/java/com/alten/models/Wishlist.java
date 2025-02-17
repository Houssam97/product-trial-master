package com.alten.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany(cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Set<Product> products;

    public Wishlist() {
        this.products = new TreeSet<>();
    }

    public void setProducts(Set<Product> products) {
        if (products != null) {
            this.products.clear();
            products.forEach(this::addProduct);
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProduct(Long productId) {
        return products.removeIf(p -> p.getId().equals(productId));
    }
}
