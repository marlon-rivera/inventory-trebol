package com.trebol.inventory.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SupplierUpdate {

    private String email;
    private String address;
    private String phone;

}
