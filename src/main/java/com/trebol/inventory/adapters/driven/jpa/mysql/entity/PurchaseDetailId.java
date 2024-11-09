package com.trebol.inventory.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDetailId implements Serializable {

    private Long purchaseId;
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDetailId that = (PurchaseDetailId) o;
        return Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, productId);
    }
}
