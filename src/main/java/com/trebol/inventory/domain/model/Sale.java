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
public class Sale implements DateSortable{

    private Long id;
    private Client client;
    private LocalDate date;
    private BigDecimal totalPrice;
    private String idInCharge;
    private List<SaleDetail> details;
    private TypeTransaction typeTransaction;
    private String nameInCharge;

}
