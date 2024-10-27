package com.trebol.inventory.configuration;

import com.google.cloud.storage.Storage;
import com.trebol.inventory.adapters.driven.firebase.FirebaseAdapter;
import com.trebol.inventory.adapters.driven.jpa.mysql.adapter.*;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.*;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.*;
import com.trebol.inventory.domain.api.*;
import com.trebol.inventory.domain.api.usecase.*;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandEntityMapper brandEntityMapper;
    private final IClientRepository clientRepository;
    private final IClientEntityMapper clientEntityMapper;
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final IUnitMeasureRepository unitMeasureRepository;
    private final IUnitMeasureEntityMapper unitMeasureEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCaseImpl(categoryPersistencePort());
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCaseImpl(brandPersistencePort());
    }

    @Bean
    public IClientPersistencePort clientPersistencePort(){
        return new ClientAdapter(clientRepository, clientEntityMapper);
    }

    @Bean
    public IClientServicePort clientServicePort(){
        return new ClientUseCaseImpl(clientPersistencePort());
    }

    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductAdapter(productRepository, productEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort(Storage storage){
        return new ProductUseCaseImpl(productPersistencePort(), categoryPersistencePort(), brandPersistencePort(), unitMeasurePersistencePort(), new FirebaseAdapter(storage));
    }

    @Bean
    public IUnitMeasurePersistencePort unitMeasurePersistencePort(){
        return new UnitMeasureAdapter(unitMeasureRepository, unitMeasureEntityMapper);
    }

    @Bean
    public IUnitMeasureServicePort unitMeasureServicePort(){
        return new UnitMeasureUseCaseImpl(unitMeasurePersistencePort());
    }
}
