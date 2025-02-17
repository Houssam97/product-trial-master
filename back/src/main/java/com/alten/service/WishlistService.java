package com.alten.service;

import com.alten.models.Wishlist;
import com.alten.models.Product;

public interface WishlistService {
    Wishlist getWishlistByUser(String username);
    void addProductToWishlist(String username, Product product);
    void removeProductFromWishlist(String username, Long productId);
}
