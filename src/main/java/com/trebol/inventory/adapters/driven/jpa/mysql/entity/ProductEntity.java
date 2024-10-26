package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
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
    @Column(nullable = false)
    private boolean active;
}
