package com.penjualan.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TransactionDetailKey implements Serializable {

    @Column(name = "DocumentNumber", length = 10)
    private String documentNumber;
    @Column(name = "ProductCode", length = 18)
    private String productCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDetailKey that = (TransactionDetailKey) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, productCode);
    }
}
