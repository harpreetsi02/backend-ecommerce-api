package com.practiceproject.ecommerce_api.service;

import com.practiceproject.ecommerce_api.entity.User;
import com.practiceproject.ecommerce_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
