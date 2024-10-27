package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

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
    private boolean active;

}
