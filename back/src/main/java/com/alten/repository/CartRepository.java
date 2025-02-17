package com.alten.repository;

import com.alten.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUsername(String username);
}
