package com.alten.service.impl;

import com.alten.models.Cart;
import com.alten.models.Product;
import com.alten.repository.CartRepository;
import com.alten.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private  CartRepository cartRepository;

    @Override
    public Cart getCartByUser(String username) {
        return cartRepository.findByUsername(username);
    }

    @Override
    public void addProductToCart(String username, Product product) {
        Cart cart = getCartByUser(username);
        cart.addProduct(product);
        cartRepository.save(cart);

    }

    @Override
    public void removeProductFromCart(String username, Long productId) {
        Cart cart = getCartByUser(username);
        cart.removeProduct(productId);
        cartRepository.save(cart);
    }
}
