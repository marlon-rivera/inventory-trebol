package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.ISupplierServicePort;
import com.trebol.inventory.domain.exception.SupplierAlreadyExistsException;
import com.trebol.inventory.domain.exception.SupplierNotExistsException;
import com.trebol.inventory.domain.model.Supplier;
import com.trebol.inventory.domain.spi.ISupplierPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SupplierUseCaseImpl implements ISupplierServicePort {

    private final ISupplierPersistencePort persistencePort;


    @Override
    public void saveSupplier(Supplier supplier) {
        if(persistencePort.getSupplierById(supplier.getId()).isPresent()) {
            throw new SupplierAlreadyExistsException();
        }
        supplier.setActive(true);
        persistencePort.saveSupplier(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return persistencePort.getAllSuppliers();
    }

    @Override
    public void updateSupplier(Supplier supplierUpdate) {
        Optional<Supplier> found = persistencePort.getSupplierById(supplierUpdate.getId());
        if(found.isEmpty()){
            throw new SupplierNotExistsException();
        }
        Supplier supplier = found.get();
        boolean changed = false;
        if(!supplier.getAddress().equals(supplierUpdate.getAddress()) && supplierUpdate.getAddress() != null) {
            supplier.setAddress(supplierUpdate.getAddress());
            changed = true;
        }
        if(!supplier.getEmail().equals(supplierUpdate.getEmail()) && supplierUpdate.getEmail() != null) {
            supplier.setEmail(supplierUpdate.getEmail());
            changed = true;
        }
        if (!supplier.getPhone().equals(supplierUpdate.getPhone()) && supplierUpdate.getPhone() != null) {
            supplier.setPhone(supplierUpdate.getPhone());
            changed = true;
        }
        if (changed) {
            persistencePort.saveSupplier(supplier);
        }
    }

    @Override
    public void deleteSupplier(String id) {
        Optional<Supplier> found = persistencePort.getSupplierById(id);
        if(found.isEmpty()) {
            throw new SupplierNotExistsException();
        }
        Supplier supplier = found.get();
        supplier.setActive(false);
        persistencePort.updateSupplier(supplier);
    }
}
