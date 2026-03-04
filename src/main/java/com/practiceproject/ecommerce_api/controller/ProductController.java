package com.practiceproject.ecommerce_api.controller;

import com.practiceproject.ecommerce_api.dto.ApiResponse;
import com.practiceproject.ecommerce_api.dto.ProductResponse;
import com.practiceproject.ecommerce_api.entity.Product;
import com.practiceproject.ecommerce_api.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

//    Add products
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

//    Get products
    @GetMapping
    public ApiResponse<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return new ApiResponse<>(
                "success",
                productService.getProducts(page,size)
        );
    }
}
