package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Purchase {

    private Long id;
    private Supplier supplier;
    private LocalDate datePurchased;
    private BigDecimal totalPurchase;
    private String idInCharge;

}
