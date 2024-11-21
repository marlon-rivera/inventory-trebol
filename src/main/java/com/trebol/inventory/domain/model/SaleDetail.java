package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SaleDetail {

    private Sale sale;
    private Batch batch;
    private Product product;
    private Integer quantitySold;
    private BigDecimal unitPrice;
    private BigDecimal ivaPrice;
    private BigDecimal subtotal;

}
