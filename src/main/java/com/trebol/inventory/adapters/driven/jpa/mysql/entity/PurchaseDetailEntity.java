package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "purchase_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailEntity {

    @EmbeddedId
    private PurchaseDetailId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id", nullable = false)
    private PurchaseEntity purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(nullable = false)
    private Integer quantityPurchased;
    @Column(nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private BigDecimal subtotal;

}