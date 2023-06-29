package com.example.ssia.entity.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private Long price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Product(String name, Long price, Currency currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }
}
