package com.alten.controller;

import com.alten.models.Cart;
import com.alten.models.Product;
import com.alten.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestBody Product product,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        cartService.addProductToCart(userDetails.getUsername(), product);
        return ResponseEntity.ok("Product added to cart!");
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.getCartByUser(userDetails.getUsername());
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        cartService.removeProductFromCart(userDetails.getUsername(), productId);
        return ResponseEntity.ok("Product removed from cart!");
    }
}
