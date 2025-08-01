package com.arise.buildbyte.repository;

import com.arise.buildbyte.model.CartItem;
import com.arise.buildbyte.model.User;
import com.arise.buildbyte.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}