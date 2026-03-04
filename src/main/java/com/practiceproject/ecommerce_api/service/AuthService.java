package com.practiceproject.ecommerce_api.service;

import com.practiceproject.ecommerce_api.dto.LoginRequest;
import com.practiceproject.ecommerce_api.entity.Role;
import com.practiceproject.ecommerce_api.entity.User;
import com.practiceproject.ecommerce_api.exception.ApiException;
import com.practiceproject.ecommerce_api.repository.UserRepository;
import com.practiceproject.ecommerce_api.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(User user){
        // Encrypted password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public String login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("User not found"));

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())){
            throw new ApiException("Invalid password");
        }

        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}
