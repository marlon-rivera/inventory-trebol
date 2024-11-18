package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.ISaleServicePort;
import com.trebol.inventory.domain.exception.BatchNotExistsException;
import com.trebol.inventory.domain.exception.ClientNotExistsException;
import com.trebol.inventory.domain.exception.QuantityInsufficientException;
import com.trebol.inventory.domain.model.*;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SaleUseCaseImpl implements ISaleServicePort {

    private final ISalePersistencePort salePersistencePort;
    private final ISaleDetailPersistencePort detailPersistencePort;
    private final IBatchPersistencePort batchPersistencePort;
    private final IAuthenticationPort authenticationPort;
    private final IProductPersistencePort productPersistencePort;
    private final IClientPersistencePort clientPersistencePort;


    @Override
    public void saveSale(List<SaleDetail> details, Client client) {
        if(clientPersistencePort.getClientById(client.getId()).isEmpty()){
            throw new ClientNotExistsException();
        }
        Sale sale = new Sale();
        BigDecimal total = BigDecimal.ZERO;
        for (SaleDetail detail : details) {
            Optional<Batch> batchOptional = batchPersistencePort.getBatchById(detail.getBatch().getId());
            if(batchOptional.isEmpty()){
                throw new BatchNotExistsException();
            }
            Batch batch = batchOptional.get();
            if(batch.getQuantityAvalaible() < detail.getQuantitySold()){
                throw new QuantityInsufficientException();
            }
            batch.setQuantityAvalaible(batch.getQuantityAvalaible() - detail.getQuantitySold());
            detail.setUnitPrice(batch.getUnitPrice());
            detail.setProduct(batch.getProduct());
            BigDecimal subtotal = calculateSubtotal(detail);
            detail.setSubtotal(subtotal);
            total = total.add(subtotal);
            batchPersistencePort.saveBatch(batch);
        }
        sale.setTotalPrice(total);
        sale.setDate(LocalDate.now());
        sale.setClient(client);
        String idInCharge = authenticationPort.getCurrentUsername();
        sale.setIdInCharge(idInCharge);
        Sale saleSaved = salePersistencePort.saveSale(sale);
        for (SaleDetail detail : details) {
            detail.setSale(saleSaved);
            detailPersistencePort.saveSaleDetail(detail);
        }
    }

    private BigDecimal calculateSubtotal(SaleDetail saleDetail) {
        BigDecimal subtotal = BigDecimal.ZERO;
        subtotal = subtotal.add(saleDetail.getUnitPrice().multiply(BigDecimal.valueOf(saleDetail.getQuantitySold())));
        return subtotal;
    }
}
