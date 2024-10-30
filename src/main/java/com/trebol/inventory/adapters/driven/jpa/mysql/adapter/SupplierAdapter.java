package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ISupplierEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ISupplierRepository;
import com.trebol.inventory.domain.model.Supplier;
import com.trebol.inventory.domain.spi.ISupplierPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SupplierAdapter implements ISupplierPersistencePort {

    private final ISupplierRepository repository;
    private final ISupplierEntityMapper mapper;

    @Override
    public void saveSupplier(Supplier supplier) {
        repository.save(mapper.toSupplierEntity(supplier));
    }

    @Override
    public Optional<Supplier> getSupplierById(String id) {
        return mapper.toOptionalSupplier(repository.findById(id));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return mapper.toSupplierList(repository.findAllByActiveTrue());
    }

    @Override
    public void deleteSupplier(String id) {
        repository.deleteById(id);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        repository.save(mapper.toSupplierEntity(supplier));
    }
}
