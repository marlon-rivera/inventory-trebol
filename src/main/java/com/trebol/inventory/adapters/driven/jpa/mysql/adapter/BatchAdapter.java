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

@RequiredArgsConstructor
public class BatchAdapter implements IBatchPersistencePort {

    private final IBatchRepository repository;
    private final IBatchEntityMapper mapper;
    private final IProductEntityMapper productMapper;

    @Override
    public List<Batch> getBatchsByProduct(Product product) {
        return mapper.toBatches(repository.findAllByProduct(productMapper.toProductEntity(product)));
    }

    @Override
    public void saveBatch(Batch batch) {
        BatchEntity batchEntity = mapper.toBatchEntity(batch);
        System.out.println(batchEntity);
        repository.save(batchEntity);
    }
}
