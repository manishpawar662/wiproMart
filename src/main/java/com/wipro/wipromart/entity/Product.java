package com.wipro.wipromart.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double productId;

    @Column(length = 50)
    private String productName;
    
    @Column
    private double productPrice;

    @Column(name = "product_mfd")
    private LocalDate mfd;
    
    @Column
    private String category;
}
