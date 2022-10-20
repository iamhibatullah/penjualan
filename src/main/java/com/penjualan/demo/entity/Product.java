package com.penjualan.demo.entity;

import com.penjualan.demo.dto.product.ProductGridDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "ProductCode", length = 18)
    private String productCode;
    @Column(name = "ProductName", length = 30)
    private String productName;
    @Column(name = "Price", precision = 8, scale = 2)
    private BigDecimal price;
    @Column(name = "Currency", length = 5)
    private String currency; //IDR
    @Column(name = "Discount", precision = 8, scale = 2)
    private BigDecimal discount; // 10% di mvc, 0.1 di database
    @Column(name = "Dimension", length = 50)
    private String dimension; //13cm x 10cm
    @Column(name = "Unit", length = 5)
    private String unit; //satuan jual produk, misal PCS
    @OneToMany(mappedBy = "product")
    private List<TransactionDetail> details;

}
