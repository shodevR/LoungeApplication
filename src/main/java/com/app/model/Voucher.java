package com.app.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer voucherId;
	private String voucherUniqueCode;
	private String voucherName;
	@ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
	private String email;
	private Integer noOfVouchers;
	@ManyToOne
    @JoinColumn(name = "packageId")
    private PackageDetails packageDetails;
	private String createdDate;
	private String issueDate;
	private String validDate;
	private String paymentType;
	private Double amount;
	private String preparedBy;
	private Double cashPaid;
	private Double dueAmount;
	private Integer claimed;
	private String qr;


}
