package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Batch {

    private Long id;
    private Product product;
    private LocalDate expirationDate;
    private Integer quantityAvalaible;

}
