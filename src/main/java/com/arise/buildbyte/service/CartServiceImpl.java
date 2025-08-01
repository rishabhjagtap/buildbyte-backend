package com.arise.buildbyte.service;

import com.arise.buildbyte.dto.CartRequestDTO;
import com.arise.buildbyte.dto.CartResponseDTO;
import com.arise.buildbyte.model.CartItem;
import com.arise.buildbyte.model.Product;
import com.arise.buildbyte.model.User;
import com.arise.buildbyte.repository.CartItemRepository;
import com.arise.buildbyte.repository.ProductRepository;
import com.arise.buildbyte.repository.UserRepository;
import com.arise.buildbyte.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void addToCart(String email, CartRequestDTO dto) {
//        User user = new User();
//        user.setId(12174L);
//        user.setEmail("user5@example.com");
//        user.setName("User5");


        User user = userRepo.findByEmail(email).orElseThrow();
        Product product = productRepo.findById(dto.getProductId()).orElseThrow();

        Optional<CartItem> existing = cartRepo.findByUserAndProduct(user, product);
        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
            cartRepo.save(item);
        } else {
            CartItem item = new CartItem();
            item.setUser(user);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            cartRepo.save(item);
        }
    }

    @Override
    public void updateQuantity(String email, Long itemId, int quantity) {
        CartItem item = cartRepo.findById(itemId).orElseThrow();
        item.setQuantity(quantity);
        cartRepo.save(item);
    }

    @Override
    public void removeItem(String email, Long itemId) {
        cartRepo.deleteById(itemId);
    }

    @Override
    public List<CartResponseDTO> getUserCart(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        List<CartItem> items = cartRepo.findByUser(user);
        return items.stream().map(item -> {
            CartResponseDTO dto = new CartResponseDTO();
            dto.setId(item.getId());
            dto.setProductName(item.getProduct().getName());
            dto.setPrice(item.getProduct().getPrice());
            dto.setQuantity(item.getQuantity());
            dto.setTotalPrice(item.getQuantity() * item.getProduct().getPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void clearCart(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        List<CartItem> items = cartRepo.findByUser(user);
        cartRepo.deleteAll(items);
    }
}