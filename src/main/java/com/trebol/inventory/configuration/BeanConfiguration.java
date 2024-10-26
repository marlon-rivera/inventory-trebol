package com.trebol.inventory.configuration;

import com.trebol.inventory.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.trebol.inventory.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.trebol.inventory.adapters.driven.jpa.mysql.adapter.ClientAdapter;
import com.trebol.inventory.adapters.driven.jpa.mysql.adapter.ProductAdapter;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IClientEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IClientRepository;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.trebol.inventory.domain.api.IBrandServicePort;
import com.trebol.inventory.domain.api.ICategoryServicePort;
import com.trebol.inventory.domain.api.IClientServicePort;
import com.trebol.inventory.domain.api.IProductServicePort;
import com.trebol.inventory.domain.api.usecase.BrandUseCaseImpl;
import com.trebol.inventory.domain.api.usecase.CategoryUseCaseImpl;
import com.trebol.inventory.domain.api.usecase.ClientUseCaseImpl;
import com.trebol.inventory.domain.api.usecase.ProductUseCaseImpl;
import com.trebol.inventory.domain.spi.IBrandPersistencePort;
import com.trebol.inventory.domain.spi.ICategoryPersistencePort;
import com.trebol.inventory.domain.spi.IClientPersistencePort;
import com.trebol.inventory.domain.spi.IProductPersistencePort;
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
    public IProductServicePort productServicePort(){
        return new ProductUseCaseImpl(productPersistencePort(), categoryPersistencePort(), brandPersistencePort());
    }
}
