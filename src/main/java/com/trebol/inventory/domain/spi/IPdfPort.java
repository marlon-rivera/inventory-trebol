package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface IPdfPort {

    Invoice generateInvoicePdf(Sale sale, List<SaleDetail> details);
    byte[] generateReportCurrentInventory(List<Product> products);
    byte[] generateReportBestSellingProduct(Product product, ProductSalesInfo bestSaleInfo);
    byte[] generateReportSales(int quantitySales, BigDecimal totalSales, BigDecimal totalGross, BigDecimal totalIva);
    byte[] generateReportPurchases(int quantityPurchases, BigDecimal totalPurchases);
    byte[] generatePdfBacthes(List<Batch> batches);

}
