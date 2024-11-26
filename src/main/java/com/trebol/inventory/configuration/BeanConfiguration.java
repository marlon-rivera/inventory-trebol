package com.trebol.inventory.configuration;

import com.google.cloud.storage.Storage;
import com.trebol.inventory.adapters.driven.authentication.AuthenticationAdapter;
import com.trebol.inventory.adapters.driven.feign.EmployeeFeignAdapter;
import com.trebol.inventory.adapters.driven.feign.IEmployeeFeignClient;
import com.trebol.inventory.adapters.driven.firebase.FirebaseAdapter;
import com.trebol.inventory.adapters.driven.jpa.mysql.adapter.*;
import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.*;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.*;
import com.trebol.inventory.adapters.driven.mail.MailAdapter;
import com.trebol.inventory.adapters.driven.pdf.PdfGeneratorAdapter;
import com.trebol.inventory.domain.api.*;
import com.trebol.inventory.domain.api.usecase.*;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

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
    private final ISupplierRepository supplierRepository;
    private final ISupplierEntityMapper supplierEntityMapper;
    private final IBatchRepository batchRepository;
    private final IBatchEntityMapper batchEntityMapper;
    private final IPurchaseRepository purchaseRepository;
    private final IPurchaseEntityMapper purchaseEntityMapper;
    private final IPurchaseDetailRepository purchaseDetailRepository;
    private final IPurchaseDetailEntityMapper purchaseDetailEntityMapper;
    private final ISaleDetailRepository saleDetailRepository;
    private final ISaleDetailEntityMapper saleDetailEntityMapper;
    private final ISaleRepository saleRepository;
    private final ISaleEntityMapper saleEntityMapper;
    private final IEmployeeFeignClient employeeFeignClient;
    private final JavaMailSender mailSender;

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
    public IBatchPersistencePort batchPersistencePort(){
        return new BatchAdapter(batchRepository, batchEntityMapper, productEntityMapper );
    }

    @Bean
    public IProductServicePort productServicePort(Storage storage){
        return new ProductUseCaseImpl(productPersistencePort(), categoryPersistencePort(), brandPersistencePort(), unitMeasurePersistencePort(), new FirebaseAdapter(storage), batchPersistencePort(), supplierPersistencePort());
    }

    @Bean
    public IUnitMeasurePersistencePort unitMeasurePersistencePort(){
        return new UnitMeasureAdapter(unitMeasureRepository, unitMeasureEntityMapper);
    }

    @Bean
    public IUnitMeasureServicePort unitMeasureServicePort(){
        return new UnitMeasureUseCaseImpl(unitMeasurePersistencePort());
    }

    @Bean
    public ISupplierPersistencePort supplierPersistencePort(){
        return new SupplierAdapter(supplierRepository, supplierEntityMapper);
    }

    @Bean
    public ISupplierServicePort supplierServicePort() {
        return new SupplierUseCaseImpl(supplierPersistencePort());
    }

    @Bean
    public IAuthenticationPort authenticationPort(){
        return new AuthenticationAdapter();
    }

    @Bean
    public IPurchaseDetailPersistencePort purchaseDetailPersistencePort(){
        return new PurchaseDetailAdapter(purchaseDetailRepository, purchaseDetailEntityMapper);
    }

    @Bean
    public IPurchasePersistencePort purchasePersistencePort(){
        return new PurchaseAdapter(purchaseRepository, purchaseEntityMapper);
    }

    @Bean
    public IPurchaseServicePort purchaseServicePort(){
        return new PurchaseUseCaseImpl(authenticationPort(), productPersistencePort(), purchasePersistencePort(), purchaseDetailPersistencePort(), batchPersistencePort());
    }

    @Bean
    public ISaleDetailPersistencePort saleDetailPersistencePort(){
        return new SaleDetailAdapter(saleDetailRepository, saleDetailEntityMapper);
    }

    @Bean
    public ISalePersistencePort salePersistencePort(){
        return new SaleAdapter(saleRepository, saleEntityMapper);
    }

    @Bean
    public IMailPort mailPort(){
        return new MailAdapter(mailSender);
    }

    @Bean
    public IPdfPort pdfPort(){
        return new PdfGeneratorAdapter();
    }

    @Bean
    public ISaleServicePort saleServicePort(){
        return new SaleUseCaseImpl(salePersistencePort(), saleDetailPersistencePort(), batchPersistencePort(), authenticationPort(), productPersistencePort(), clientPersistencePort(), mailPort(), pdfPort());
    }

    @Bean
    public IEmployeeClient employeeClient(){
        return new EmployeeFeignAdapter(employeeFeignClient);
    }

    @Bean
    public ITransactionServicePort transactionServicePort(){
        return new TransactionUseCaseImpl(salePersistencePort(), saleDetailPersistencePort(), purchasePersistencePort(), purchaseDetailPersistencePort(), employeeClient(), productPersistencePort(), batchPersistencePort(), pdfPort());
    }
}
