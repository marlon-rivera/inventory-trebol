package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.ITransactionServicePort;
import com.trebol.inventory.domain.exception.TransactionNotFoundException;
import com.trebol.inventory.domain.model.*;
import com.trebol.inventory.domain.spi.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
public class TransactionUseCaseImpl implements ITransactionServicePort {

    private final ISalePersistencePort salePersistencePort;
    private final ISaleDetailPersistencePort saleDetailPersistencePort;
    private final IPurchasePersistencePort purchasePersistencePort;
    private final IPurchaseDetailPersistencePort purchaseDetailPersistencePort;
    private final IEmployeeClient employeeClient;
    private final IProductPersistencePort productPersistencePort;
    private final IBatchPersistencePort batchPersistencePort;
    private final IPdfPort pdfPort;

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
        Collections.reverse(transactions);
        return transactions;
    }

    @Override
    public Invoice generateReport(ReportType type, Dates dates) {
        if(type.equals(ReportType.CURRENT_INVENTORY)){
            return generateReportCurrentInventory();
        }
        if(type.equals(ReportType.BEST_SELLING_PRODUCT)){
            return generateReportBestSellingProduct(dates.getStart(), dates.getEnd());
        }
        if(type.equals(ReportType.SALES)){
            return generateReportSales(dates);
        }
        if(type.equals(ReportType.PURCHASES)){
            return generateReportPurchases(dates);
        }
        return new Invoice("", null);
    }

    private Invoice generateReportCurrentInventory(){
        List<Product> products = productPersistencePort.getAllProducts();
        for(Product product: products){
            product.setBatches(batchPersistencePort.getBatchsByProduct(product));
        }
        return new Invoice("REPORTE_INVENTARIO_ACTUAL", pdfPort.generateReportCurrentInventory(products)) ;
    }

    private Invoice generateReportBestSellingProduct(LocalDate start, LocalDate end){
        List<Sale> sales = salePersistencePort.getSalesInRange(start, end);
        Map<Product, ProductSalesInfo> productSales = new HashMap<>();
        if(sales.isEmpty()){
            throw new TransactionNotFoundException();
        }
        for (Sale sale : sales) {
            List<SaleDetail> details = saleDetailPersistencePort.loadSaleDetailsBySaleId(sale.getId());

            for (SaleDetail detail : details) {
                Product product = detail.getProduct();
                Integer quantitySold = detail.getQuantitySold();
                BigDecimal subtotal = detail.getSubtotal();

                ProductSalesInfo salesInfo = productSales.getOrDefault(product, new ProductSalesInfo());
                salesInfo.addSales(quantitySold, subtotal);
                productSales.put(product, salesInfo);
            }
        }

        Product bestSellingProduct = null;
        ProductSalesInfo bestSalesInfo = null;

        for (Map.Entry<Product, ProductSalesInfo> entry : productSales.entrySet()) {
            if (bestSalesInfo == null || entry.getValue().getQuantitySold() > bestSalesInfo.getQuantitySold()) {
                bestSellingProduct = entry.getKey();
                bestSalesInfo = entry.getValue();
            }
        }
        byte[] pdf = pdfPort.generateReportBestSellingProduct(bestSellingProduct, bestSalesInfo);
        return new Invoice("REPORTE_PRODUCTO_MAS_VENDIDO", pdf);
    }

    private Invoice generateReportSales(Dates dates){
        List<Sale> sales = salePersistencePort.getSalesInRange(dates.getStart(), dates.getEnd());
        BigDecimal totalSales = sales.stream()
                .map(Sale::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalIva = sales.stream()
                .map(Sale::getIva)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGross = sales.stream()
                .map(Sale::getGrossPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        byte[] pdf =pdfPort.generateReportSales(sales.size(), totalSales, totalGross, totalIva);
        return new Invoice("REPORTE_TOTAL_VENTAS", pdf);
    }

    private Invoice generateReportPurchases(Dates dates){
        List<Purchase> purchases = purchasePersistencePort.getPurchasesInRange(dates.getStart(), dates.getEnd());
        BigDecimal totalPurchases = purchases.stream()
                .map(Purchase::getTotalPurchase)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        byte[] pdf =pdfPort.generateReportPurchases(purchases.size(), totalPurchases);
        return new Invoice("REPORTE_TOTAL_COMPRAS", pdf);
    }


}
