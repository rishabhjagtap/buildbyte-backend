package com.arise.buildbyte.controller;

import com.arise.buildbyte.dto.ProductRequestDTO;
import com.arise.buildbyte.dto.ProductResponseDTO;
import com.arise.buildbyte.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
