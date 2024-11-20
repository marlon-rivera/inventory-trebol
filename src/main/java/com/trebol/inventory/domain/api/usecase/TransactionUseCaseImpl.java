package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.ITransactionServicePort;
import com.trebol.inventory.domain.model.*;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class TransactionUseCaseImpl implements ITransactionServicePort {

    private final ISalePersistencePort salePersistencePort;
    private final ISaleDetailPersistencePort saleDetailPersistencePort;
    private final IPurchasePersistencePort purchasePersistencePort;
    private final IPurchaseDetailPersistencePort purchaseDetailPersistencePort;
    private final IEmployeeClient employeeClient;

    @Override
    public List<DateSortable> getTransactions() {
        List<Sale> sales = salePersistencePort.getSales();
        List<Purchase> purchases = purchasePersistencePort.getPurchases();
        List<DateSortable> transactions = new ArrayList<>();
        for (Sale sale : sales) {
            sale.setDetails(saleDetailPersistencePort.loadSaleDetailsBySaleId(sale.getId()));
            for (SaleDetail saleDetail: sale.getDetails()){
                saleDetail.setSale(null);
            }
            sale.setTypeTransaction(TypeTransaction.SALE);
            sale.setNameInCharge(employeeClient.getEmployeeName(sale.getIdInCharge()));
            transactions.add(sale);
        }
        for (Purchase purchase : purchases) {
            purchase.setDetails(purchaseDetailPersistencePort.loadPurchaseDetailsByIdPurchase(purchase.getId()));
            for (PurchaseDetail purchaseDetail: purchase.getDetails()){
                purchaseDetail.setPurchase(null);
            }
            purchase.setTypeTransaction(TypeTransaction.PURCHASE);
            purchase.setNameInCharge(employeeClient.getEmployeeName(purchase.getIdInCharge()));
            transactions.add(purchase);
        }
        transactions.sort(Comparator.comparing(DateSortable::getDate));
        return transactions;
    }
}
