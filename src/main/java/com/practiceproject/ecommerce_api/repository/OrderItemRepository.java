package com.practiceproject.ecommerce_api.repository;

import com.practiceproject.ecommerce_api.entity.Cart;
import com.practiceproject.ecommerce_api.entity.CartItem;
import com.practiceproject.ecommerce_api.entity.OrderItem;
import com.practiceproject.ecommerce_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
