package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesInfo {

    private int quantitySold = 0;
    private BigDecimal totalSales = BigDecimal.ZERO;

    public void addSales(int quantity, BigDecimal subtotal) {
        this.quantitySold += quantity;
        this.totalSales = this.totalSales.add(subtotal);
    }
}
