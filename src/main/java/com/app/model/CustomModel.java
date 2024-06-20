package com.app.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomModel {
    private Integer voucherId;
    private String voucherUniqueCode;
    private String voucherName;

    private String clientName;
    private String discount;
    private String email;
    private Integer noOfVouchers;
    private Integer packageId;
    private String issueDate;
    private String validDate;
    private String paymentType;
    private Integer amount;
    private String preparedBy;
    private Integer claimed;
    private Integer SId;
    private  String uniqueCode;

    private boolean flag;

}




