package com.trebol.inventory.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Alert {


    private Product product;
    private TypeAlert typeAlert;
    private Integer quantityAvalaible;
    private LocalDate expirationDate;

    public Alert(Product product, TypeAlert typeAlert, Integer quantityAvalaible) {
        this.product = product;
        this.typeAlert = typeAlert;
        this.quantityAvalaible = quantityAvalaible;
    }

    public Alert(Product product, TypeAlert typeAlert, LocalDate expirationDate, Integer quantityAvalaible) {
        this.product = product;
        this.typeAlert = typeAlert;
        this.expirationDate = expirationDate;
        this.quantityAvalaible = quantityAvalaible;
    }
}
