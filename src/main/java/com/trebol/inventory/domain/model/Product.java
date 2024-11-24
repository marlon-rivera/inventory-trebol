package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
    private List<Batch> batches;
    private int totalAvailable;
    private boolean active;
    private List<Supplier> suppliers;
    private LocalDate expirationDate;

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
                this.batches,
                this.totalAvailable,
                this.active,
                this.suppliers,
                this.expirationDate
        );
    }
}
