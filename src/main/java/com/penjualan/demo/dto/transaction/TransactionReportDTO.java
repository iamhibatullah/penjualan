package com.penjualan.demo.dto.transaction;

import com.penjualan.demo.entity.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReportDTO {

    private String transaction;
    private String user;
    private BigDecimal total;
    private LocalDate date;
    private List<TransactionDetail> item;
}
