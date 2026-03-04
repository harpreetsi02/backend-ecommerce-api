package com.practiceproject.ecommerce_api.service;

import com.practiceproject.ecommerce_api.dto.ProductResponse;
import com.practiceproject.ecommerce_api.entity.Product;
import com.practiceproject.ecommerce_api.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProduct(){
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice()
                ))
                .toList();
    }

    public Page<ProductResponse> getProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable)
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ));
    }
}
