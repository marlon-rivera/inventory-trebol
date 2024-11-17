package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import com.trebol.inventory.domain.model.TypeSupplier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "supplier")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private TypeSupplier type;
    @Column(nullable = false)
    private boolean active;
    @ManyToMany(mappedBy = "suppliers")
    private List<ProductEntity> products;

}
