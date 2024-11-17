package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private List<SupplierEntity> suppliers;
}
