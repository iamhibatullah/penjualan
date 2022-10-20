package com.penjualan.demo.dao;

import com.penjualan.demo.dto.transaction.TransactionReportDTO;
import com.penjualan.demo.entity.TransactionHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionHeader, String> {

}
