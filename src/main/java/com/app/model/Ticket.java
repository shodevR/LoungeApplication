package com.app.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketId;
    private String ticketType;
    @ManyToOne
    @JoinColumn(name = "voucherId")
    private Voucher voucher;
	private String pnr;
	@ManyToOne
	@JoinColumn(name = "partnerId")
	private Partners partners;
	private String sequence;
	private String airlineName;
	private String flightNumber;
	private String firstName;
	private String lastName;
	private String date;
	@ManyToOne
    @JoinColumn(name = "packageId")
    private PackageDetails packageDetails;
	private String doc;
	private String doc2;
	private String doc3;
	private String doc4;
	private String paymentMethod;
	private double Amount;
	private String voucherCode;
	private String createdBy;
	
	

}
