package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface IProductServicePort {

    void createProduct(Product product, MultipartFile image) throws Exception;
    void updateProduct(Product product, BigDecimal newPrice);
    void deleteProduct(String id);
    List<ProductsCategory> getAllProducts();
    String generateIdProduct(Category category);
    List<Alert> getAllAlerts();
    List<Product> getProducts();
    List<ProductsSupplier> getProductsSuppliers();

}
