package com.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer SId;
    private  String uniqueCode;
    private Integer voucherid;
    private String expire;
    private boolean flag;

}

