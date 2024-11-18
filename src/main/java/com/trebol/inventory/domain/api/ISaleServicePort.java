package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Client;
import com.trebol.inventory.domain.model.SaleDetail;

import java.util.List;

public interface ISaleServicePort {

    void saveSale(List<SaleDetail> details, Client client);

}
