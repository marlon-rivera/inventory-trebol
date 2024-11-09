package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity(name = "batch")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    private LocalDate expirationDate;
    @Column(nullable = false)
    private Integer quantityAvalaible;

}
