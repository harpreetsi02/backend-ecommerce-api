package com.practiceproject.ecommerce_api.service;

import com.practiceproject.ecommerce_api.entity.*;
import com.practiceproject.ecommerce_api.exception.ApiException;
import com.practiceproject.ecommerce_api.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        CartItemRepository cartItemRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

//    Place the order
    @Transactional
    public String placeOrder(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Cart cart = cartRepository.findByUser(user).orElseThrow();

        List<CartItem> items = cartItemRepository.findAll()
                .stream()
                .filter(i -> i.getCart().getId().equals(cart.getId()))
                .toList();

        if (items.isEmpty()){
            throw new ApiException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        double total = 0;

        order = orderRepository.save(order);

        for (CartItem item : items){
            Product product = item.getProduct();

            // Stock Reduce
            product.setStock(
                    product.getStock() - item.getQuantity()
            );
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItemRepository.save(orderItem);

            total += product.getPrice() * item.getQuantity();
        }

        order.setTotalPrice(total);
        orderRepository.save(order);

        // Cart clear
        cartItemRepository.deleteAll(items);

        return "Order placed successfully";
    }

//    Get My Order list
    public List<Order> getMyOrders() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return orderRepository.findByUser(user);
    }
}
