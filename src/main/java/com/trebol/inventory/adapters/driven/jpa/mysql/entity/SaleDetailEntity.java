package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import com.trebol.inventory.domain.model.Product;
import com.trebol.inventory.domain.model.Sale;

import java.math.BigDecimal;

public class SaleDetailEntity {


    private Long id;
    private Sale sale;
    private Product product;
    private Integer quantitySold;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

}
