package com.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partners {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer partnerId;
	private String partnerType;
	private String partnerName;
	private String conformationType;
	private String sequence;
	private String discount;
	@ManyToOne
	@JoinColumn(name = "packageId")
	private PackageDetails packageDetails;
	private String createdBy;
	private Integer count;
	
	
}
