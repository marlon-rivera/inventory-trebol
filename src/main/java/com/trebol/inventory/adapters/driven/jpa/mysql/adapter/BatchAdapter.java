package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.BatchEntity;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IBatchEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IBatchRepository;
import com.trebol.inventory.domain.model.Batch;
import com.trebol.inventory.domain.model.Product;
import com.trebol.inventory.domain.spi.IBatchPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BatchAdapter implements IBatchPersistencePort {

    private final IBatchRepository repository;
    private final IBatchEntityMapper mapper;
    private final IProductEntityMapper productMapper;

    @Override
    public List<Batch> getBatchsByProduct(Product product) {
        return mapper.toBatches(repository.findAllByProductAndQuantityAvalaibleGreaterThan(productMapper.toProductEntity(product), 0));
    }

    @Override
    public Batch saveBatch(Batch batch) {
        BatchEntity batchEntity = mapper.toBatchEntity(batch);
        return mapper.toBatch(repository.save(batchEntity));
    }

    @Override
    public Optional<Batch> getBatchById(Long id) {
        return mapper.toOptionalBatch(repository.findById(id));
    }

    @Override
    public void saveAll(List<Batch> batches) {
        List<BatchEntity> batchEntities = mapper.toBatchEntities(batches);
        repository.saveAll(batchEntities);
    }
}
