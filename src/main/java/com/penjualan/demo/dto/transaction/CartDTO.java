package com.penjualan.demo.dto.transaction;

import com.penjualan.demo.entity.Product;
import com.penjualan.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


public class CartDTO {

    private List<CartDetailDTO> detail = new LinkedList<>();
    private BigDecimal total;

    public List<CartDetailDTO> getDetail(){
        return this.detail;
    }

    public void addProducts(CartDetailDTO products){
        this.detail.add(products);
    }

    public BigDecimal getTotal(){return this.total;}

    public void setTotal(BigDecimal total){this.total = total;}
}
