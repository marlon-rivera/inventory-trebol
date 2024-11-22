package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Client;
import com.trebol.inventory.domain.model.Invoice;
import com.trebol.inventory.domain.model.SaleDetail;
import com.trebol.inventory.domain.model.TypeInvoice;

import java.time.LocalDate;
import java.util.List;

public interface ISaleServicePort {

    Invoice saveSale(List<SaleDetail> details, Client client, TypeInvoice typeInvoice, LocalDate saleDate);

}
