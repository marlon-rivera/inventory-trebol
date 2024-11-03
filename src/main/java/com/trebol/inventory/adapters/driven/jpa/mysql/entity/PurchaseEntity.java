package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "purchase")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;
    @Column(nullable = false)
    private LocalDate datePurchased;
    @Column(nullable = false)
    private BigDecimal totalPurchase;
    @Column(nullable = false)
    private String idInCharge;
}
