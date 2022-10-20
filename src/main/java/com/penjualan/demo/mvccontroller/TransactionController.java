package com.penjualan.demo.mvccontroller;

import com.penjualan.demo.dto.transaction.TransactionReportDTO;
import com.penjualan.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/report")
    public String getReport(Model model){

        List<TransactionReportDTO> grid = transactionService.getTransactionReport();
        model.addAttribute("grid" ,grid);

        return null;
    }

}
