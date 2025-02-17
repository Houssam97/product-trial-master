package com.alten.service;

import com.alten.models.Cart;
import com.alten.models.Product;

public interface CartService {
    Cart getCartByUser(String username);
    void addProductToCart(String username, Product product);
    void removeProductFromCart(String username, Long productId);
}
