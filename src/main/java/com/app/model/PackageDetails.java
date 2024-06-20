package com.app.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer packageId;
	private String packageName;
	private Integer hour;
	private String times;
	private String image;
	private String drinks;
	private String softDrink;
	private String breakfast;
	private String lunch;
	private String Dinner;
	private String snack;
	private boolean wifi;
	private Integer amount;
	private Boolean flag;
	
	
	
	
	
	

}
