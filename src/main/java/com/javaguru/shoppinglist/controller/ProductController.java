package com.javaguru.shoppinglist.controller;


import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated({ProductDTO.Create.class}) @RequestBody ProductDTO productDTO,
                                       UriComponentsBuilder builder) {
        Long id = productService.create(productDTO);
        return ResponseEntity.created(builder.path("/products/{id}").buildAndExpand(id).toUri()).build();
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
}
