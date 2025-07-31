package com.arise.buildbyte.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private String brand;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private String imageUrl;
}
