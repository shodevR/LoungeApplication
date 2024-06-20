package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

	
	  @Query("select i from Inventory i where i.inventoryCategory.inventoryCategoryName = ?1"
	  ) public List<Inventory>viewByCategoryName(String cname);
	  
	 
}
