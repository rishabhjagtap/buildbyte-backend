package com.arise.buildbyte.dto;

import lombok.Data;

@Data
public class CartResponseDTO {
    private Long id;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
}
