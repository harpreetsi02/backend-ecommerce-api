package com.practiceproject.ecommerce_api.repository;

import com.practiceproject.ecommerce_api.entity.Product;
import com.practiceproject.ecommerce_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
