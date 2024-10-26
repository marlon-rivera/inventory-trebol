package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsCategory {

    private Category category;
    private List<Product> products;

}
