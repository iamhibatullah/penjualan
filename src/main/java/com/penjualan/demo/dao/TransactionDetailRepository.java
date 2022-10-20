package com.penjualan.demo.dao;

import com.penjualan.demo.entity.TransactionDetail;
import com.penjualan.demo.entity.TransactionDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, TransactionDetailKey> {
}
