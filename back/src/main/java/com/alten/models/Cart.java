package com.alten.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;
import java.util.TreeSet;

@Entity
@Getter @Setter
public class Cart extends DomainEntity {
    private String username;
    @OneToMany(cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Set<Product> products;

    public Cart() {
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

    public void removeProduct(Long productId) {
        products.removeIf(p -> p.getId().equals(productId));
    }
}
