package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.IPurchaseServicePort;
import com.trebol.inventory.domain.exception.ProductNotExistsException;
import com.trebol.inventory.domain.model.Batch;
import com.trebol.inventory.domain.model.Purchase;
import com.trebol.inventory.domain.model.PurchaseDetail;
import com.trebol.inventory.domain.model.Supplier;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class PurchaseUseCaseImpl implements IPurchaseServicePort {

    private final IAuthenticationPort authenticationPort;
    private final IProductPersistencePort productPersistencePort;
    private final IPurchasePersistencePort purchasePersistencePort;
    private final IPurchaseDetailPersistencePort purchaseDetailPersistencePort;
    private final IBatchPersistencePort batchPersistencePort;

    @Override
    public void createPurchase(Supplier supplier, List<PurchaseDetail> detailList, LocalDate purchaseDate) {
        String idCurrentUser = authenticationPort.getCurrentUsername();
        BigDecimal totalPurchase = BigDecimal.ZERO;
        Purchase purchase = new Purchase(null, supplier, purchaseDate, null, idCurrentUser);
        for (PurchaseDetail detail : detailList) {
            if(productPersistencePort.getProductById(detail.getProduct().getId()).isEmpty()){
                throw new ProductNotExistsException();
            }
            BigDecimal subtotal = calculateSubtotal(detail);
            detail.setSubtotal(subtotal);
            totalPurchase = totalPurchase.add(subtotal);
        }
        purchase.setTotalPurchase(totalPurchase);
        Purchase purchaseSaved = purchasePersistencePort.savePurchase(purchase);
        for (PurchaseDetail detail : detailList) {
            detail.setPurchase(purchaseSaved);
        }
        purchaseDetailPersistencePort.savePurchaseDetails(detailList);
        for (PurchaseDetail detail : detailList) {
            Batch batch = new Batch(null, detail.getProduct(), detail.getProduct().getExpirationDate(), detail.getQuantityPurchased(), detail.getUnitPrice());
            batchPersistencePort.saveBatch(batch);
        }
    }

    private BigDecimal calculateSubtotal(PurchaseDetail detail) {
        BigDecimal subtotal = BigDecimal.ZERO;
        subtotal = subtotal.add(detail.getPurchasePrice().multiply(BigDecimal.valueOf(detail.getQuantityPurchased())));
        return subtotal;
    }
}
