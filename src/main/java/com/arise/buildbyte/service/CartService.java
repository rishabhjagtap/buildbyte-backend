package com.arise.buildbyte.service;

import com.arise.buildbyte.dto.CartRequestDTO;
import com.arise.buildbyte.dto.CartResponseDTO;

import java.util.List;

public interface CartService {
    void addToCart(String userEmail, CartRequestDTO dto);
    void updateQuantity(String userEmail, Long itemId, int quantity);
    void removeItem(String userEmail, Long itemId);
    List<CartResponseDTO> getUserCart(String userEmail);
    void clearCart(String userEmail);
}

