package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierPersistencePort {

    void saveSupplier(Supplier supplier);
    Optional<Supplier> getSupplierById(String id);
    List<Supplier> getAllSuppliers();
    void deleteSupplier(String id);
    void updateSupplier(Supplier supplier);

}
