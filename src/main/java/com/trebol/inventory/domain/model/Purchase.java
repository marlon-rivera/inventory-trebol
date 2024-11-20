package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Purchase implements DateSortable{

    private Long id;
    private Supplier supplier;
    private LocalDate datePurchased;
    private BigDecimal totalPurchase;
    private String idInCharge;
    private List<PurchaseDetail> details;
    private TypeTransaction typeTransaction;
    private String nameInCharge;

    @Override
    public LocalDate getDate() {
        return datePurchased;
    }
}
