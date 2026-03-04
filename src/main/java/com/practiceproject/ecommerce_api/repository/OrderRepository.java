package com.practiceproject.ecommerce_api.repository;

import com.practiceproject.ecommerce_api.entity.Order;
import com.practiceproject.ecommerce_api.entity.OrderItem;
import com.practiceproject.ecommerce_api.entity.User;
import org.aspectj.weaver.Lint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
