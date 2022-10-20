package com.penjualan.demo.service;

import com.penjualan.demo.dao.ProductRepository;
import com.penjualan.demo.dto.product.ProductGridDTO;
import com.penjualan.demo.dto.transaction.CartDTO;
import com.penjualan.demo.dto.transaction.CartDetailDTO;
import com.penjualan.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private final int rowsInPage = 10;

    @Override
    public List<ProductGridDTO> getProductGrid(Integer page, String name) {
        Pageable pageable = PageRequest.of(page-1, rowsInPage, Sort.by("id"));



        return productRepository.findAll(name, pageable);
    }

    @Override
    public Long getTotalPages(String name) {
        double totalData = (double)(productRepository.count(name));
        long totalPage = (long)(Math.ceil(totalData / rowsInPage));

        return totalPage;
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findById(code).get();
    }

    @Override
    public void addProductToCart(String code, CartDTO cart) {
        Product prod = findByCode(code);
        CartDetailDTO detailDTO = new CartDetailDTO();
        detailDTO.setProductName(prod.getProductName());
        detailDTO.setProductCode(prod.getProductCode());
        detailDTO.setPrice(prod.getPrice());
        detailDTO.setPriceUnit(prod.getUnit());
        detailDTO.setQuantity(1);
        if(prod.getDiscount().equals(0)){
            detailDTO.setDiscount(new BigDecimal(0));
            detailDTO.setDiscountPrice(prod.getPrice());
        } else{
            detailDTO.setDiscount(prod.getDiscount());
            double countPrice = prod.getPrice().doubleValue()
                    - (prod.getPrice().doubleValue() * (prod.getDiscount().doubleValue() / 100));

            detailDTO.setDiscountPrice(BigDecimal.valueOf(countPrice));
        }
        double countTotal =detailDTO.getDiscountPrice().doubleValue() * detailDTO.getQuantity();
        detailDTO.setSubTotal(BigDecimal.valueOf(countTotal));

        cart.addProducts(detailDTO);
    }

    @Override
    public void addProductQuantityToCart(String code, CartDTO cart) {
        cart.getDetail().stream().forEach(each -> {
            if (each.getProductCode().equals(code)) {
                each.setQuantity(each.getQuantity()+1);
                double countTotal = each.getDiscountPrice().doubleValue() * each.getQuantity();
                each.setSubTotal(BigDecimal.valueOf(countTotal));
            }
        });
    }
}
