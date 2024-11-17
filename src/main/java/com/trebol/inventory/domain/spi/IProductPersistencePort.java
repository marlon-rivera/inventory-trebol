package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Product;
import com.trebol.inventory.domain.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface IProductPersistencePort {

    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    List<Product> getAllProducts();
    Integer countProductsByCategory(Long id);
    Optional<Product> getProductById(String id);
    List<Product> getProductsBySupplier(String supplierId);

}
