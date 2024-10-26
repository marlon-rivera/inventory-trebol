package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {

    private String id;
    private String name;
    private String email;
    private String phone;

}
