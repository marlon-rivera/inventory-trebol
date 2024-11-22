package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Invoice;
import com.trebol.inventory.domain.model.Sale;
import com.trebol.inventory.domain.model.SaleDetail;

import java.util.List;

public interface IPdfPort {

    Invoice generateInvoicePdf(Sale sale, List<SaleDetail> details);

}
