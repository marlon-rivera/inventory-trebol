package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.IProductServicePort;
import com.trebol.inventory.domain.exception.BrandNotExistsException;
import com.trebol.inventory.domain.exception.CategoryNotExistsException;
import com.trebol.inventory.domain.exception.ProductNotExistsException;
import com.trebol.inventory.domain.exception.UnitMeasureNotExistsException;
import com.trebol.inventory.domain.model.Category;
import com.trebol.inventory.domain.model.Product;
import com.trebol.inventory.domain.model.ProductsCategory;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductUseCaseImpl implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;
    private final IUnitMeasurePersistencePort unitMeasurePersistencePort;
    private final IStorageImagePort storageImagePort;

    @Override
    public void createProduct(Product product, MultipartFile image) throws Exception {
        Optional<Category> categoryOp = categoryPersistencePort.getCategoryById(product.getCategory().getId());
        if(categoryOp.isEmpty()) throw new CategoryNotExistsException();
        if(brandPersistencePort.getBrandById(product.getBrand().getId()).isEmpty()) throw new BrandNotExistsException();
        if(unitMeasurePersistencePort.getUnitMeasureById(product.getUnitMeasure().getId()).isEmpty()) throw new UnitMeasureNotExistsException();
        product.setImage(storageImagePort.uploadImage(image));
        product.setActive(true);
        String productId = generateIdProduct(categoryOp.get());
        product.setId(productId);
        System.out.println(product.getImage());
        System.out.println(product.getImage().length());
        productPersistencePort.createProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productPersistencePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Product> productOp = productPersistencePort.getProductById(id);
        if(productOp.isEmpty()) throw new ProductNotExistsException();
        Product product = productOp.get();
        product.setActive(false);
        productPersistencePort.deleteProduct(product);
    }

    @Override
    public List<ProductsCategory> getAllProducts() {
        List<Product> products = productPersistencePort.getAllProducts();
        List<Category> categories = products.stream()
                .map(Product::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        List<ProductsCategory> productsCategories = new ArrayList<>();
        List<Product> productsByCategory = new ArrayList<>();
        for (Category category : categories) {
            for (Product product : products) {
                if (category.getId().equals(product.getCategory().getId())) {
                    productsByCategory.add(product);
                }
            }
            productsCategories.add(new ProductsCategory(category, new ArrayList<>(productsByCategory)));
            productsByCategory.clear();
        }
        return productsCategories;
    }

    @Override
    public String generateIdProduct(Category category) {
        return category.getName().toUpperCase().substring(0, 3) + productPersistencePort.countProductsByCategory(category.getId());
    }
}
