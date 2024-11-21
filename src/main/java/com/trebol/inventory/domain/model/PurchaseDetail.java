package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDetail {

    private Purchase purchase;
    private Product product;
    private Batch batch;
    private Integer quantityPurchased;
    private BigDecimal unitPrice;
    private BigDecimal purchasePrice;
    private BigDecimal subtotal;

}
