package com.penjualan.demo.service;

import com.penjualan.demo.dao.LoginRepository;
import com.penjualan.demo.dao.TransactionDetailRepository;
import com.penjualan.demo.dao.TransactionRepository;
import com.penjualan.demo.dto.transaction.CartDTO;
import com.penjualan.demo.dto.transaction.CartDetailDTO;
import com.penjualan.demo.dto.transaction.TransactionReportDTO;
import com.penjualan.demo.entity.Product;
import com.penjualan.demo.entity.TransactionDetail;
import com.penjualan.demo.entity.TransactionHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public void saveTransaction(CartDTO cart, String user) {

        TransactionHeader header = new TransactionHeader();
        List<TransactionDetail> detailList = new LinkedList<>();
        header.setDetails(detailList);
        header.setDocumentCode("TRX");
        if(transactionRepository.count() < 9){
            header.setDocumentNumber("00" + (transactionRepository.count() + 1 ));
        } else if(transactionRepository.count() > 9){
            header.setDocumentNumber("0" + (transactionRepository.count() + 1));
        } else if(transactionRepository.count() > 99){
            header.setDocumentNumber("" + (transactionRepository.count() + 1));
        }
        header.setDate(LocalDate.now());
        header.setTotal(cart.getTotal());
        header.setUsername(loginRepository.findById(user).get());
        transactionRepository.save(header);

        for (CartDetailDTO detailDTO: cart.getDetail()) {
            Product product = productService.findByCode(detailDTO.getProductCode());
            TransactionDetail detail = new TransactionDetail();
            detail.setProduct(product);
            detail.setHeader(header);
            detail.setQuantity(detailDTO.getQuantity());
            detail.setPrice(detailDTO.getDiscountPrice());
            detail.setCurrency(product.getCurrency());
            detail.setSubTotal(detailDTO.getSubTotal());
            detail.setUnit(product.getUnit());
            detail.setDocumentCode(header.getDocumentCode());
            transactionDetailRepository.save(detail);
            detailList.add(detail);
        }

        transactionRepository.save(header);
    }

    @Override
    public List<TransactionReportDTO> getTransactionReport() {

        List<TransactionHeader> headers = transactionRepository.findAll();
        List<TransactionReportDTO> dto = new LinkedList<>();

        for (TransactionHeader each: headers) {
            TransactionReportDTO entity = new TransactionReportDTO();
            entity.setTransaction(each.getDocumentCode() + " - " +each.getDocumentNumber());
            entity.setDate(each.getDate());
            entity.setTotal(each.getTotal());
            entity.setUser(each.getUsername().getUsername());
            entity.setItem(each.getDetails());
            dto.add(entity);
        }

        return dto;
    }
}
