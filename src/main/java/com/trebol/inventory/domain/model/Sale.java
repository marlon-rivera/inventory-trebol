package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Sale implements DateSortable{

    private Long id;
    private Client client;
    private LocalDate date;
    private BigDecimal totalPrice;
    private BigDecimal grossPrice;
    private BigDecimal iva;
    private String idInCharge;
    private List<SaleDetail> details;
    private TypeTransaction typeTransaction;
    private String nameInCharge;

}
