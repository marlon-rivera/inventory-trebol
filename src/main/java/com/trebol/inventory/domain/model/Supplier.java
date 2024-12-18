package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private TypeSupplier type;
    private boolean active;

}
