package com.arise.buildbyte.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private String category;
    private String imageUrl;
}
