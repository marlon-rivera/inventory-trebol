package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String image;
    @Column(nullable = false)
    private Integer minStock;
    @Column(nullable = false)
    private Integer maxStock;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;
    @ManyToOne
    @JoinColumn(name = "unit_measure_id", nullable = false)
    private UnitMeasureEntity unitMeasure;
    @Column(nullable = false)
    private String measuredValue;
    @Column(nullable = false)
    private boolean active;
}
