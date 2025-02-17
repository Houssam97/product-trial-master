package com.alten.controller;

import com.alten.models.Wishlist;
import com.alten.models.Product;
import com.alten.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<String> addProductToWishlist(@RequestBody Product product,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        wishlistService.addProductToWishlist(userDetails.getUsername(), product);
        return ResponseEntity.ok("Product added to wishlist!");
    }

    @GetMapping
    public ResponseEntity<Wishlist> getWishlist(@AuthenticationPrincipal UserDetails userDetails) {
        Wishlist wishlist = wishlistService.getWishlistByUser(userDetails.getUsername());
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProductFromWishlist(@PathVariable Long productId,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        wishlistService.removeProductFromWishlist(userDetails.getUsername(), productId);
        return ResponseEntity.ok("Product removed from wishlist!");
    }
}
