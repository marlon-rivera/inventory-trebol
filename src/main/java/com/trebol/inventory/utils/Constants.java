package com.trebol.inventory.utils;

import java.math.BigDecimal;

public class Constants {

    private Constants() { }

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTH = "Authorization";
    public static final String ROLE_ = "ROLE_";
    public static final String ROLE = "ROLE";
    public static final String BRAND_ALREADY_EXISTS = "La marca que desea crear, ya existe.";
    public static final String CATEGORY_ALREADY_EXISTS = "La categoria que desea crear, ya existe.";
    public static final String BRAND_NOT_EXISTS = "La marca que se requiere no existe.";
    public static final String CATEGORY_NOT_EXISTS = "La categoria que se requiere no existe.";
    public static final String PRODUCT_NOT_EXISTS = "El producto solicitado no existe.";
    public static final String CLIENT_ALREADY_EXISTS = "El cliente que desea crear, ya existe.";
    public static final String UNIT_MEASURE_ALREADY_EXISTS = "La unidad de medida que desea crear, ya existe.";
    public static final String UNIT_MEASURE_NOT_EXISTS = "La unidad de medida solicitada no existe.";
    public static final String SUPPLIER_ALREADY_EXISTS = "El proveedor que desea guardar ya existe.";
    public static final String SUPPLIER_NOT_EXISTS = "El proveedor que se requiere no existe.";
    public static final String CLIENT_NOT_EXISTS = "El cliente que se requiere no existe.";
    public static final String BATCH_NOT_EXISTS = "El lote que requiere del producto, no existe: ";
    public static final String QUANTITY_INSUFFICIENT = "La cantidad requerida del producto no esta disponible: ";
    public static final BigDecimal PERCENTAGE_IVA = BigDecimal.valueOf(0.19);

}
