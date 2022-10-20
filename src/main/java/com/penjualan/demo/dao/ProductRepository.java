package com.penjualan.demo.dao;

import com.penjualan.demo.dto.product.ProductGridDTO;
import com.penjualan.demo.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = """
            SELECT new com.penjualan.demo.dto.product.ProductGridDTO(pro.productCode, 
            pro.productName, pro.price, pro.discount)
            FROM Product as pro
            WHERE pro.productName LIKE %:name%
            """)
    List<ProductGridDTO> findAll(@Param("name") String name, Pageable pageable);

    @Query("""
            SELECT COUNT(pro.productName)
            FROM Product as pro
            WHERE pro.productName LIKE %:name%
            """)
    long count(@Param("name") String name);
}
