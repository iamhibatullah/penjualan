package com.penjualan.demo.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGridDTO {

    private String productCode;
    private String productName;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal discountPrice;


    public ProductGridDTO(String productCode, String productName, BigDecimal price, BigDecimal discount){
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        double countPrice = price.doubleValue() - (price.doubleValue() * (discount.doubleValue() / 100));
        this.discountPrice = BigDecimal.valueOf(countPrice);
    }
}
