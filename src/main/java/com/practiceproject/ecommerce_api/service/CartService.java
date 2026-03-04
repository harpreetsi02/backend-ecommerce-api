package com.practiceproject.ecommerce_api.service;

import com.practiceproject.ecommerce_api.entity.Cart;
import com.practiceproject.ecommerce_api.entity.CartItem;
import com.practiceproject.ecommerce_api.entity.Product;
import com.practiceproject.ecommerce_api.entity.User;
import com.practiceproject.ecommerce_api.exception.ApiException;
import com.practiceproject.ecommerce_api.repository.CartItemRepository;
import com.practiceproject.ecommerce_api.repository.CartRepository;
import com.practiceproject.ecommerce_api.repository.ProductRepository;
import com.practiceproject.ecommerce_api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

//    Add to cart
    public String addToCart(Long productId, int quantity){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Cart cart = cartRepository
                .findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Product product = productRepository
                .findById(productId)
                .orElseThrow();

        // Stock check
        if(product.getStock() < quantity){
            throw new ApiException("Not enough stock");
        }

        Optional<CartItem> existingItem =
                cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);

            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);

            cartItemRepository.save(newItem);
        }
        return "Product added to cart";
    }

//    Get my cart
    public List<CartItem> getMyCart() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Cart cart = cartRepository.findByUser(user).orElseThrow();

        return cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getCart().getId().equals(cart.getId()))
                .toList();
    }
}
