package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.ISaleServicePort;
import com.trebol.inventory.domain.exception.BatchNotExistsException;
import com.trebol.inventory.domain.exception.ClientNotExistsException;
import com.trebol.inventory.domain.exception.QuantityInsufficientException;
import com.trebol.inventory.domain.model.*;
import com.trebol.inventory.domain.spi.*;
import com.trebol.inventory.utils.Constants;
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
    private final IMailPort mailPort;
    private final IPdfPort pdfPort;

    @Override
    public Invoice saveSale(List<SaleDetail> details, Client client, TypeInvoice typeInvoice, LocalDate date) {
        Optional<Client> clientOp = clientPersistencePort.getClientById(client.getId());
        if(clientOp.isEmpty()){
            throw new ClientNotExistsException();
        }
        Client clientFind = clientOp.get();
        Sale sale = new Sale();
        sale.setClient(clientFind);
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalGross = BigDecimal.ZERO;
        for (SaleDetail detail : details) {
            Optional<Batch> batchOptional = batchPersistencePort.getBatchById(detail.getBatch().getId());
            if(batchOptional.isEmpty()){
                throw new BatchNotExistsException();
            }
            Batch batch = batchOptional.get();
            if(batch.getQuantityAvalaible() < detail.getQuantitySold()){
                throw new QuantityInsufficientException(batch.getProduct().getName());
            }
            batch.setQuantityAvalaible(batch.getQuantityAvalaible() - detail.getQuantitySold());


            detail.setUnitPrice(batch.getUnitPrice().subtract(batch.getUnitPrice().multiply(Constants.PERCENTAGE_IVA)));
            detail.setIvaPrice(batch.getUnitPrice().multiply(Constants.PERCENTAGE_IVA));
            detail.setProduct(batch.getProduct());
            detail.setBatch(batch);

            BigDecimal subtotal = calculateSubtotal(detail);
            BigDecimal subtotalGross = calculateSubtotalGross(detail);
            detail.setSubtotal(subtotal);
            total = total.add(subtotal);
            totalGross = totalGross.add(subtotalGross);
            batchPersistencePort.saveBatch(batch);
        }
        sale.setTotalPrice(total);
        sale.setGrossPrice(totalGross);
        sale.setDate(date);
        sale.setIva(calculateTotalIva(details));
        String idInCharge = authenticationPort.getCurrentUsername();
        sale.setIdInCharge(idInCharge);
        Sale saleSaved = salePersistencePort.saveSale(sale);
        for (SaleDetail detail : details) {
            detail.setSale(saleSaved);
            detailPersistencePort.saveSaleDetail(detail);
        }

        if(typeInvoice.equals(TypeInvoice.ELECTRONIC)){
            mailPort.sendInvoice(clientFind.getEmail(), details, saleSaved);
        }else if(typeInvoice.equals(TypeInvoice.PHYSICAL)){
            return pdfPort.generateInvoicePdf(saleSaved, details);
        }
        return new Invoice("", null);
    }

    private BigDecimal calculateSubtotalGross(SaleDetail saleDetail) {
        BigDecimal subtotal = BigDecimal.ZERO;
        subtotal = subtotal
                .add(
                        saleDetail
                                .getUnitPrice()
                                .multiply(BigDecimal.valueOf(saleDetail.getQuantitySold())));
        return subtotal;
    }

    private BigDecimal calculateSubtotal(SaleDetail saleDetail) {
        BigDecimal subtotal = BigDecimal.ZERO;
        subtotal = subtotal
                .add(
                        saleDetail
                                .getUnitPrice()
                                .add(saleDetail
                                        .getIvaPrice())
                                .multiply(BigDecimal.valueOf(saleDetail.getQuantitySold())));
        return subtotal;
    }

    private BigDecimal calculateTotalIva(List<SaleDetail> details){
        BigDecimal total = BigDecimal.ZERO;
        for (SaleDetail detail : details) {
            total = total.add(detail.getIvaPrice().multiply(BigDecimal.valueOf(detail.getQuantitySold())));
        }
        return total;
    }
}
