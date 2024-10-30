package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Supplier;

import java.util.List;

public interface ISupplierServicePort {

    void saveSupplier(Supplier supplier);
    List<Supplier> getAllSuppliers();
    void updateSupplier(Supplier supplier);
    void deleteSupplier(String id);

}
