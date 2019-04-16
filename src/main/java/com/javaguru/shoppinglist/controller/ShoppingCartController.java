package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated({ShoppingCartDTO.Create.class}) @RequestBody ShoppingCartDTO shoppingCartDTO,
                                       UriComponentsBuilder builder) {
        Long id = shoppingCartService.create(shoppingCartDTO);
        return ResponseEntity.created(builder.path("/carts/{id}").buildAndExpand(id).toUri()).build();
    }

    @GetMapping(params = "name")
    public ShoppingCartDTO findCartByName(@RequestParam("name") String name) {
        return shoppingCartService.findByName(name);
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO findCartById(@PathVariable("id") Long id) {
        return shoppingCartService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }
}
