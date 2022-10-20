package com.penjualan.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TransactionHeader")
public class TransactionHeader {


    @Column(name = "DocumentCode", length = 3)
    private String documentCode; //TRX
    @Id
    @Column(name = "DocumentNumber", length = 10)
    private String documentNumber;
    @ManyToOne
    @JoinColumn(name = "Username")
    private Login username;
    @Column(name = "Total", precision = 10, scale = 2)
    private BigDecimal total;
    @Column(name = "Date", length = 10)
    private LocalDate date; //format dd-MM-yyyy (20-05-2000)
    @OneToMany(mappedBy = "header")
    private List<TransactionDetail> details;
}
