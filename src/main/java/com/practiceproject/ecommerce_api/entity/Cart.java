package com.practiceproject.ecommerce_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    public Cart(){}

    public Long getId(){ return id; }

    public User getUser(){ return user; }
    public void setUser(User user){ this.user = user; }
}
