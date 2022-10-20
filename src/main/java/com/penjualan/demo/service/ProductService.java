package com.penjualan.demo.service;

import com.penjualan.demo.dto.product.ProductGridDTO;
import com.penjualan.demo.dto.transaction.CartDTO;
import com.penjualan.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductGridDTO> getProductGrid(Integer page, String name);

    Long getTotalPages(String name);

    Product findByCode(String code);

    void addProductToCart(String code, CartDTO cart);

    void addProductQuantityToCart(String code, CartDTO cart);
}
