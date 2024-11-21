package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Sale;
import com.trebol.inventory.domain.model.SaleDetail;

import java.util.List;

public interface IMailPort {

    void sendInvoice(String to, List<SaleDetail> details, Sale sale);

}
