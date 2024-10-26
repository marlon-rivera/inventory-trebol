package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Category;
import com.trebol.inventory.domain.model.Product;
import com.trebol.inventory.domain.model.ProductsCategory;

import java.util.List;

public interface IProductServicePort {

    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String id);
    List<ProductsCategory> getAllProducts();
    String generateIdProduct(Category category);

}
