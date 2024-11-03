package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@ToString

public class Product {

    private String id;
    private String name;
    private String description;
    private String image;
    private Integer minStock;
    private Integer maxStock;
    private Category category;
    private Brand brand;
    private UnitMeasure unitMeasure;
    private String measuredValue;
    private int quantityAvalaible;
    private Long batchId;
    private LocalDate expirationDate;
    private boolean active;

    public Product createCopy(){
        return new Product(
                this.id,
                this.name,
                this.description,
                this.image,
                this.minStock,
                this.maxStock,
                this.category,
                this.brand,
                this.unitMeasure,
                this.measuredValue,
                this.quantityAvalaible,
                this.batchId,
                this.expirationDate,
                this.active
        );
    }
}
