package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private String idInCharge;
    @Column(nullable = false)
    private BigDecimal grossPrice;
    @Column(nullable = false)
    private BigDecimal iva;

}
