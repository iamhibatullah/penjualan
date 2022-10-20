package com.penjualan.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransactionDetail {

    @EmbeddedId
    private TransactionDetailKey id = new TransactionDetailKey();

    @ManyToOne
    @MapsId("documentNumber")
    @JoinColumn(name = "DocumentNumber")
    private TransactionHeader header;

    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "ProductCode")
    private Product product;

    @Column(name = "DocumentCode")
    private String documentCode;
    @Column(name = "Price", precision = 8, scale = 2)
    private BigDecimal price;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "SubTotal", precision = 12, scale = 2)
    private BigDecimal subTotal;
    @Column(name = "Currency")
    private String currency;

}
