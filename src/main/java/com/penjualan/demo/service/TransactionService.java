package com.penjualan.demo.service;

import com.penjualan.demo.dto.transaction.CartDTO;
import com.penjualan.demo.dto.transaction.TransactionReportDTO;

import java.util.List;

public interface TransactionService {
    void saveTransaction(CartDTO cart, String user);

    List<TransactionReportDTO> getTransactionReport();
}
