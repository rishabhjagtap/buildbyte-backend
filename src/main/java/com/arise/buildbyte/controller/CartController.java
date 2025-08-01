package com.arise.buildbyte.controller;


import com.arise.buildbyte.dto.CartRequestDTO;
import com.arise.buildbyte.dto.CartResponseDTO;
import com.arise.buildbyte.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    String email = "user5@example.com";

    @PostMapping("/add")
    public void addToCart(@RequestBody CartRequestDTO dto, Principal principal) {
        cartService.addToCart(email, dto);
    }

    @PutMapping("/update/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestParam int quantity, Principal principal) {
        cartService.updateQuantity(email, itemId, quantity);
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeItem(@PathVariable Long itemId, Principal principal) {
        cartService.removeItem(email, itemId);
    }

    @GetMapping
    public List<CartResponseDTO> getCart(Principal principal) {
        return cartService.getUserCart(email);
    }

    @DeleteMapping("/clear")
    public void clearCart(Principal principal) {
        cartService.clearCart(email);
    }
}
