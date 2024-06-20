package com.app.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	private String itemName;
	private String itemCode;
	
	private Integer quantity;
	private String purchaseDate;
	private String expireDate;
	private Integer minStockLevel;
	private Integer maxStockLevel;
	
	@Embedded
	private InventoryCategory inventoryCategory;
	
	

}
