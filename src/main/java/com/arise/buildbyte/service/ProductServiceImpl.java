package com.arise.buildbyte.service;

import com.arise.buildbyte.dto.ProductRequestDTO;
import com.arise.buildbyte.dto.ProductResponseDTO;
import com.arise.buildbyte.model.Product;
import com.arise.buildbyte.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepo;

    private Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());
        return product;
    }

    private ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setImageUrl(product.getImageUrl());
        return dto;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepo.save(product);
        return toDTO(saved);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return toDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());

        return toDTO(productRepo.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
