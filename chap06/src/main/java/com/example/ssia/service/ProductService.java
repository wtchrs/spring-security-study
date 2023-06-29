package com.example.ssia.service;

import com.example.ssia.entity.product.Product;
import com.example.ssia.entity.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findAll() {
        return productRepository.findAll(Pageable.ofSize(20));
    }

    public Page<Product> findAll(int page) {
        return productRepository.findAll(PageRequest.of(page - 1, 20));
    }
}
