package com.trebol.inventory.domain.model;

public enum TypeSupplier {

    NATURAL("Natural"),
    LEGAL("Jurídico");

    private final String name;

    TypeSupplier(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
