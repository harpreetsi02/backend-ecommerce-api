package com.practiceproject.ecommerce_api.controller;

import com.practiceproject.ecommerce_api.entity.Cart;
import com.practiceproject.ecommerce_api.entity.CartItem;
import com.practiceproject.ecommerce_api.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

//    Add cart items
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity){
        return cartService.addToCart(productId, quantity);
    }

//    View the cart
    @GetMapping
    public List<CartItem> viewCart(){
        return cartService.getMyCart();
    }
}
