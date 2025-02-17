package com.alten.service.impl;

import com.alten.models.Wishlist;
import com.alten.models.Product;
import com.alten.repository.WishlistRepository;
import com.alten.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private  WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist getWishlistByUser(String username) {
        return wishlistRepository.findByUsername(username);
    }

    @Override
    public void addProductToWishlist(String username, Product product) {
        Wishlist wishlist = getWishlistByUser(username);
        wishlist.addProduct(product);
        wishlistRepository.save(wishlist);
    }

    @Override
    public void removeProductFromWishlist(String username, Long productId) {
        Wishlist wishlist = getWishlistByUser(username);
        wishlist.removeProduct(productId);
        wishlistRepository.save(wishlist);
    }
}
