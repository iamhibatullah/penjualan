package com.penjualan.demo.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {

    private String productCode;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private String priceUnit;
    private BigDecimal discount;
    private BigDecimal discountPrice;
    private BigDecimal subTotal;

}
